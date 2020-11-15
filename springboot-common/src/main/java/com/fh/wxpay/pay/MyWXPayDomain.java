package com.fh.wxpay.pay;

public class MyWXPayDomain implements IWXPayDomain {

    public void report(String domain, long elapsedTimeMillis, Exception ex) {

    }

    public DomainInfo getDomain(WXPayConfig config) {
        // 域名
        // 该域名是否为主域名
        DomainInfo domainInfo = new DomainInfo("api.mch.weixin.qq.com",true);
        return domainInfo;
    }

}
