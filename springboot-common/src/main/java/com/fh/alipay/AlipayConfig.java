package com.fh.alipay;

/**
 * @author huangp
 * @create 2020-10-13 17:15
 */
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102600761389";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCc+3IFit6KQIijuIMDsyJFDeNS6CmEeosWSglrA2+EXObkqT65BlHja7ohMV4h3P1+qUAsssrnjJgwpbB+OYMmeHz+k3dcx4+NnbevD0HqH9AdKaDWeAbJE0AmhiwdrOEJ7zXSiEtMGsl1xDrBYyGDCKvu0wBVdOVtIqeuNBqx2v8PxNtr4jxteqz2dD+i3iyJVOEoK0PN8Kp4XhTYK21IEiBo78bbCbpSLqfiEhQTuNMIqCskujA0s4S7pODA3Kk0qbHqFu/4Ti3DbQl4sYF1Ia19DUSlDKoegYg32rkDdPICGj0oUy85juc8vIw/UhC859L6mUH9yaksWieqsZbLAgMBAAECggEANtCGC/Flq5jFHYX8GXqfaMVkrzpp5Nx1XJ8VksxsUCewZ//A6xKN3X9sStLWL7Tevy+SPQlhWGD7c2Ens6uZZAGGg84yS69TLPv5e6M0PPBG1ygGBV174RJbwBfxp5sKKVHh1VXKFRT1a63NZmGa/go1HvuGjlB3HBiRvH5tI9TsKvw//Sp7cZrsazMs9Bi0b3MbWlc/3j4IvW329zLIa3qq/l4S4nse+Qy0KhQ8Uqtnvs3pHonH/3dsfgEWWAiOb0ge+SVVZp+Qd2QPgpXRgSSh19l8/e4Ed74VhB4LFTjOcv4QTsySytS0XAT95E2CFOZIHmhOyChFPpvC7MfLAQKBgQDUBXmO4rBaeglPjSZfKB0L1eIT8crae6DqIlJyDwJg8RlSteg180ejlO/S01Xow3izCncqiLvqMm+F829akjejbnvE2vHNfxUu9D9IFCmasnkWbfRPSRCqIDJrn3JXlTOyZyKxkqFQ9cJslFLbfHO6dagsNC0ZN1JkNcDuXbMnKwKBgQC9i1dNgShMwxbRdl//c+8quQCTKKef5Y3Tzqd8mmyqc6pJ84scBNJQ3T5TkneLZgAy2gRYZhW8zJ6y39zgKB+xP4F9LSrKAiOSTviygcfjo8JJU8rR7QWYBoOWGs6Azo10/cN3kq/WN4soLiXU/fyPQnTjtp8z6YODtjIbzl9+4QKBgQDDqvHOHgLm3O0uz3L0Ko7z10W3ZlPxYlrU+SvhO1zUnmziWiKdd5FLtC3IAWWtVIhVo3jqdhoGPtmRY2IG0h4q49GxkOXK4ldoZ9zm5kSnEcKSIpNvgFyyxNgDXrklfvqg0HHCMzJRBx2RGymEicw77k1vzg840EdnV/xff3XQ1QKBgQCeLEOkd93LgRnvnhsqL/Mr8cqbA1guxP9/+3olLzDzZSZ8I/K4ZQWlWZuDXjzIZUxRI0JxCm+laBQfLVVC8fjQjzBd521HulU46RH81PSb8LJXQseEO4lpfjYc8y3Uq4m8UW/mvtBhsWQ9GZWUkodKfajRxjpAteN77HUQYz2pwQKBgFbC4aYsq08bjGtlW7L+lzWkkyD4XRzL+X8r5628AJSoQILhFKLmVdq7nvfRXtYVeHjsguF05T0k6XG6DH5jrK3HincaP4qLhq8o3FJt4iqJSn1oF9VQwJPFBDaQmYqqKJceHGAzaHVdi43ZV9fvuP/TWXbLKhDnw1GMwJx8m5+u";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiFxRM+GRitUUIxD/m7kU6YDjxwo7DH8z81m4/qmHvzLqEJQCxD72peCN3FT7PEObtcTSjOL66DtM4SiT/G67/ThFC5Rvt8MXfUQQkBLuiGRT6pK7kIvcMddIlnTfZ/XezIi6uqbNTIdRvfRouas/kUGVR9qfM2D/dq5miIR3mO27DlWHyaASyx2QY2jUahTDmBxk7FxiC/zYWyRBxkEwKoZHjAEzInY8976m8a+nJAdmipHkfj4ltWopsNdr1rH708YVAT3nHxnAjsJVxKUeteapgUsRteEcQIzvJUuXEjZEGbZAsGZ2WE76F+rhYUJ12CyoHkybfZzqHXbJAuywfQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://jbb449.natappfree.cc/order/queryAliOrderStatus";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://j9hg5j.natappfree.cc/order/queryAliOrderStatus";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}
