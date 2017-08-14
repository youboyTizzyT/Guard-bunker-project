package com.company.util;

import com.company.ServerHandler.Split;
import com.company.suanfa.AlgorithmComputingDistance;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by weicong on 17-8-9.
 */
public class test {
    public static void main(String[] args) {

Jedis jedis;
        com.company.redis.JedisPool.initepool();
        jedis= com.company.redis.JedisPool.getJedis();
        String[] aaa=jedis.hget("flist","fid").split("\\|");
        String[] bbb=aaa[0].split("\\,");
        int a=Integer.parseInt(bbb[0]);
        int b=Integer.parseInt(bbb[1]);
        System.out.println(a+b);


    }
}
