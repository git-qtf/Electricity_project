package com.fh.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisPool {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
   // @Value("${spring.redis.password}")
   // private String password;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;




    private static JedisPool jedisPool;
    @Bean
    public  JedisPool initPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMinIdle(100);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestOnBorrow(true);
       // jedisPool = new JedisPool(jedisPoolConfig, host, port,timeout,password);
         jedisPool = new JedisPool(jedisPoolConfig, host, port);
        return RedisPool.jedisPool;
    }




    public static Jedis getResource() {
        return jedisPool.getResource();
    }
}
