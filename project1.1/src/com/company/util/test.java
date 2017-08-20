package com.company.util;

import com.company.ServerHandler.Split;
import com.company.suanfa.AlgorithmComputingDistance;
import io.netty.buffer.ByteBuf;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weicong on 17-8-9.
 */
public class test {
    public static void main(String[] args) {
        Jedis jedis;
        jedis=new Jedis("127.0.0.1",6379);

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
