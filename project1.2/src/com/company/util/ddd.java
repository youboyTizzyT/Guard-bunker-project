package com.company.util;

import com.company.Businesslogic.Attack;
import com.company.ServerHandler.Subpub;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by weicong on 17-8-17.
 */
public class ddd {
    public static void main(String[] args) {
        Jedis jedis;
        jedis = new Jedis("127.0.0.1", 6379);
        String mb=jedis.hget("111|HYOGOLIST","Infantry");
        int a=Integer.parseInt(mb);
        System.out.println(a);
    }
}

