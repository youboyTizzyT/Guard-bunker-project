package com.company.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.Scanner;

/**
 * Created by weicong on 17-8-8.
 */
public class bbb extends ChannelInboundHandlerAdapter{
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println((String)msg);
        ctx.write("123");
    }
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Scanner scanner=new Scanner(System.in);
        ctx.writeAndFlush(scanner.next());
    }

    public void channelActive(ChannelHandlerContext ctx) {
ctx.writeAndFlush("123");


    }

}
