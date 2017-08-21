package com.company.util;

import com.company.Businesslogic.Attack;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by weicong on 17-8-17.
 */
public class ccc {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        System.out.println(jedis.hget("COLLECT","1234")==null);



    }
}
