package com.company.Handler;

//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by weicong on 17-8-4.
 */
public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {
//    private static Logger   logger  = LoggerFactory.getLogger(HelloClientIntHandler.class);

    @Override
    // 读取服务端的信息
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String[] massage=((String) msg).split("\\|");

//        System.out.println("Ir");
//        System.out.println(msg);
//        ByteBuf result = (ByteBuf) msg;
//        byte[] result1 = new byte[result.readableBytes()];
//        result.readBytes(result1);
//        result.release();

//        System.out.println("Server said:" + new String(result1));
//        if (massage[0].equals("ATTACK")){
//            System.out.println(msg);
        new Thread(){
            Scanner scanner=new Scanner(System.in);
            @Override
            public void run() {
                String a=scanner.next();
                ctx.write(a);
                ctx.flush();
                System.out.println(a);

            }
        }.start();
//        System.out.println("12312312313213213");

if(massage[0].equals("WITHDRAW")){
    ctx.close();
}

//            ctx.fireChannelInactive();
//        }
        System.out.println(msg);
//        System.out.println( "156156");
//        ctx.close();
    }
    @Override
    // 当连接建立的时候向服务端发送消息 ，channelActive 事件当连接建立的时候会触发
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        logger.info("HelloClientIntHandler.channelActive");
        System.out.println("IA");
//        String msg = "Subscribe|123|9";
        String msg="LOGIN|111|222";
//        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
//        encoded.writeBytes(msg.getBytes());
//        ctx.write(encoded);
        ctx.write(msg);
//        ctx.write(msg);
//        ctx.write(msg);
//        ctx.write(msg);
        ctx.flush();

    }
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
       ctx.writeAndFlush(msg);
    }
}