package com.fh.order.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fh.alipay.AlipayConfig;
import com.fh.cart.model.Cart;
import com.fh.common.Idempotence;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.common.UserCustomAnnotations;
import com.fh.order.biz.OrderService;
import com.fh.user.model.User;
import com.fh.utils.IdUtil;
import com.fh.utils.RedisUtil;
import com.fh.wxpay.pay.MyConfig;
import com.fh.wxpay.pay.WXPay;
import com.fh.wxpay.pay.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author huangp
 * @create 2020-11-05 19:11
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 生成订单
     *
     * @param cartList
     * @param payType
     * @param addressId
     * @param totalPrice
     * @param user
     * @return
     */
    @Ignore
    @Idempotence
    @RequestMapping("buildOrder")
    public ServerResponse buildOrder(String cartList, Integer payType, Integer addressId, Integer totalPrice, @UserCustomAnnotations User user) {

        if (cartList != null) {
            List cartList1 = (List) JSON.parseArray(cartList, Cart.class);
            return orderService.buildOrder(cartList1, payType, addressId, totalPrice, user);
        }
        return ServerResponse.error();
    }


    /**
     * 设置幂等性
     */
    @RequestMapping("getToken")
    public ServerResponse getToken() {
        String mtoken = IdUtil.createId();
        RedisUtil.setEx(mtoken, mtoken, 30 * 60);
        return ServerResponse.success(mtoken);
    }


    @Ignore
    @RequestMapping("updateStatus")
    public Map<String, String> updateStatus(HttpServletRequest request) {
        System.out.println("=======微信回调==========");
        Map<String, String> map = new HashMap<>();
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            String resultxml = new String(outSteam.toByteArray(), "utf-8");


            //String notifyData = outSteam.toString(); // 支付结果通知的xml格式数据

            MyConfig config = new MyConfig();


            Map<String, String> notifyMap = WXPayUtil.xmlToMap(resultxml);  // 转换成map

            if (!WXPayUtil.isSignatureValid(resultxml, config.getKey())) {
                // 签名正确 修改订单状态
                if (!notifyMap.get("return_code").equals("SUCCESS")) {
                    System.out.println("======" + notifyMap.get("return_msg"));
                    return null;
                }
                if (!notifyMap.get("result_code").equals("SUCCESS")) {
                    System.out.println("======" + notifyMap.get("err_code_des"));
                    return null;
                }
                //支付成功修改订单状态
                String out_trade_no = notifyMap.get("out_trade_no").toString();
                orderService.updateStatus(out_trade_no);
                map.put("return_code", "SUCCESS");
                return map;
            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 微信回调
     *
     * @param orderNo
     * @return
     */
    @Ignore
    @RequestMapping("queryOrderStatus")
    public ServerResponse queryOrderStatus(String orderNo) {

        try {
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);

            Map<String, String> data = new HashMap<String, String>();
            data.put("out_trade_no", orderNo);
            int count = 0;
            for (; ; ) {
                Map<String, String> resp = wxpay.orderQuery(data);
                System.out.println(resp);

                if (resp.get("return_code").equals("SUCCESS") && resp.get("result_code").equals("SUCCESS") && resp.get("trade_state").equals("SUCCESS")) {
                    //支付成功 跳支付成功页面
                    orderService.updateStatus(orderNo);
                    return ServerResponse.success();
                }
                Thread.sleep(3000);
                count++;
                if (count >= 30) {//大于40是两分钟
                    return ServerResponse.error("支付超时！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error(e.getMessage());
        }

    }

    /**
     * 支付宝回调
     *
     * @param request
     * @return
     */
    @Ignore
    @RequestMapping("queryAliOrderStatus")
    public String queryAliOrderStatus(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        System.out.println("========================回调成功");
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");


            orderService.updateStatus(out_trade_no);
            return "支付成功";
        }else {//验证失败

            return "失败";
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
    }

    /**
     * 查询所有订单
     * @return
     */
    @RequestMapping("queryOrder")
    public ServerResponse queryOrder(@UserCustomAnnotations User user){

        return orderService.queryOrder(user);
    }
}
