package com.company.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weicong on 17-8-15.
 */
public class test111 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        JedisPubSub jedisPubSub=new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
                System.out.println(message);

            }
        };
        jedis.subscribe(jedisPubSub,"JRedisChat");
    }
}
