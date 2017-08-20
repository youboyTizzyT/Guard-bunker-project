package com.company.ServerHandler;

import com.company.Businesslogic.Attack;
import com.company.Businesslogic.Reinforce;
import com.company.Businesslogic.Withdraw;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by weicong on 17-8-17.
 */
public class HttpInboundHandlerLong extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String[] massage=((String) msg).split("\\|");
//        System.out.println("111!");
        if("LONG".equals(massage[0])){//LONG|uid,fid    //LONG|111,111
            new Thread(){
                @Override
                public void run() {
                    Subpub subpub=new Subpub();
                    subpub.subpub("MAKETRUE|"+massage[1].split("\\,")[1],ctx);
                }
            }.start();

//            System.out.println("订阅");
//            Jedis jedis=new Jedis("127.0.0.1",6379);
//            jedis.set(massage[1].split("\\,")[1]+"fid",(String)msg);
            ctx.writeAndFlush("TURE|YOUCANATTACK");
        }
        //attack|uid,fid|10,20    ATTACK|111,111|10,20
        else if("ATTACK".equals(massage[0])){
            Attack attack=new Attack();
            ctx.writeAndFlush(attack.attack(massage[1],massage[2]));


        }
        //增援     REINFORCE|uid,fid|10     REINFORCE|111,111|10
        else if ("REINFORCE".equals(massage[0])) {
            Reinforce reinforce=new Reinforce();
            ctx.writeAndFlush(reinforce.reinforce(massage[1],massage[2]));
        }else if ("WITHDRAW".equals(massage[0])) {
//                   WITHDRAW
            Withdraw withdraw=new Withdraw();
            ctx.writeAndFlush(withdraw.withdraw(massage[1],massage[2]));

        }else {
//            System.out.println(1111);
            ctx.fireChannelRead(msg);
//        }
        }

//        System.out.println("Client said:" + resultStr);
//        String response = "I am ok!";
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(msg);
    }
//
}
