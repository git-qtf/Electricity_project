package com.fh.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fh.alipay.AlipayConfig;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author huangp
 * @create 2020-10-13 19:24
 */
@CrossOrigin
@RestController
@RequestMapping("alipay")
public class AlipayController {

    @Ignore
    @RequestMapping("alipayPoen")
    public ServerResponse alipayPoen(BigDecimal totalPrice,String orderNumber) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(orderNumber);
        //付款金额，必填
        String total_amount = new String(String.valueOf(totalPrice));
        //订单名称，必填
        String subject = new String("XX超市");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        try {
            String body = alipayClient.pageExecute(alipayRequest).getBody();
            return ServerResponse.success(body);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }
}

