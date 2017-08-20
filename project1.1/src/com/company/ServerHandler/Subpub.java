package com.company.ServerHandler;

import io.netty.channel.ChannelHandlerContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by weicong on 17-8-17.
 */
public class Subpub {
    public String bbb="";
    public String subpub(String a, ChannelHandlerContext ctx){
        Jedis jedis;
        jedis=new Jedis("127.0.0.1",6379);
        JedisPubSub jedisPubSub=new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
                System.out.println(message);
                bbb=message;
                ctx.write(bbb);
                ctx.flush();
                System.out.println(message);
            }
        };
        jedis.subscribe(jedisPubSub,a);

//        System.out.println(bbb);
            return bbb;
    }
}
class ddd {
//    public String aaa(){
//        Subpub subpub=new Subpub();
//        subpub.subpub("MAKETRUE|123");
//        return subpub.bbb;
//    }
//
//    public static void main(String[] args) {
//        ddd ddd=new ddd();
//
//        System.out.println(ddd.aaa());
//    }

}
