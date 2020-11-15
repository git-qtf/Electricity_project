package com.fh.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
    //过期时间
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    //私钥
    private static final String TOKEN_SECRET = "iii.p.ertert.oaif-ryt24!@#yyyyy$(*&$(";

    /**
     * 生成签名，15分钟过期
     * @param **username**
    * @param **password**
    * @param userString
     * @return
     */
    public static String sign(String userString) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("user", userString)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param **token**
    * @return
     */
    public static String verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userStr = jwt.getClaim("user").asString();
            return userStr;
        } catch (Exception e){
            return "";
        }
    }

    public static void main(String[] args) {

        String verify = verify("eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJleHAiOjE2MDQzODY0MzIsInVzZXIiOiJ7XCJjcmVhdGVEYXRlXCI6MTYwMjY5MTIwMDAwMCxcInBhc3N3b3JkXCI6XCIxMTFcIixcInBob25lXCI6XCIxNzc5NjY4ODk3MVwiLFwidXNlckF2YXRhclwiOlwiaHR0cHM6Ly9xaXRmLm9zcy1jbi1iZWlqaW5nLmFsaXl1bmNzLmNvbS8yMDIwLTEwLTI4LzElMjAlMjgyJTI5LmpwZ1wiLFwidXNlcklkXCI6MSxcInVzZXJOYW1lXCI6XCIxMTFcIn0ifQ.byP_q5E3HQn7YccEcU_eyT2p02Yblbx47yu2TIDTmZ4");
        System.out.println(verify);
    }
}
