package com.fh.wxpay.pay;

import java.io.InputStream;

public class MyConfig extends WXPayConfig {

    // 商家注册之后会给你一个应用ID
    @Override
    String getAppID() {
        return "wxa1e44e130a9a8eee";
    }

    // 商户号
    @Override
    String getMchID() {
        return "1507758211";
    }

    @Override
    public String getKey() {
        return "feihujiaoyu12345678yuxiaoyang123";
    }

    @Override
    InputStream getCertStream() {
        return null;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        MyWXPayDomain wxPayDomain = new MyWXPayDomain();
        return wxPayDomain;
    }
}
