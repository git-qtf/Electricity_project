package com.fh.wxpay.controller;

import com.fh.common.ServerResponse;
import com.fh.wxpay.pay.MyConfig;
import com.fh.wxpay.pay.WXPay;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("wxPay")
public class WxPaymentController {

    /*二维码路径*/
    @RequestMapping("code")
    public ServerResponse code(String orderNumber){
        MyConfig config = new MyConfig();
        try {
            WXPay wxpay = new WXPay(config);

            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "飞狐电商支付"); // 支付中的主题
            data.put("out_trade_no", orderNumber); // 订单号
            data.put("fee_type", "CNY"); // 货币单位：分

         /*   BigDecimal multiply = moneyCount.multiply(BigDecimal.valueOf(100));
            String value = String.valueOf(multiply);
            value = value.substring(0,value.lastIndexOf("."));*/

            data.put("total_fee", "1"); //  1分


            // 终端ip ,记录ip,可以发现攻击我们的ip并进行 屏蔽
            data.put("spbill_create_ip", "123.12.12.123");
            // 重点：回调地址，用来通知支付结果的地址 (让别人，使用穿透工具进行访问)
            data.put("notify_url", "http://33gk3k.natappfree.cc/order/updateStatus");
            data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
            data.put("product_id", "12");

            Map<String, String> resp = wxpay.unifiedOrder(data);

            return ServerResponse.success(resp);

        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }

    }


}
