package com.company.util;

import redis.clients.jedis.Jedis;

/**
 * Created by weicong on 17-8-17.
 */
public class test2222 {
    public static void main(String[] args){
        Jedis jRedis = new Jedis("localhost");
        jRedis.publish("JRedisChat","my name is chenLong");
        jRedis.publish("JRedisChat","Hello chenLong!");
    }
}
