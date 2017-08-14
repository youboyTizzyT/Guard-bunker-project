package com.company.Handler;

//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by weicong on 17-8-4.
 */
public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {
//    private static Logger   logger  = LoggerFactory.getLogger(HelloClientIntHandler.class);

    @Override
    // 读取服务端的信息
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("Ir");
//        ByteBuf result = (ByteBuf) msg;
//        byte[] result1 = new byte[result.readableBytes()];
//        result.readBytes(result1);
//        result.release();

//        System.out.println("Server said:" + new String(result1));

        System.out.println( msg);
        ctx.close();
    }
    @Override
    // 当连接建立的时候向服务端发送消息 ，channelActive 事件当连接建立的时候会触发
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        logger.info("HelloClientIntHandler.channelActive");
        System.out.println("IA");
        String msg = "123";
//        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
//        encoded.writeBytes(msg.getBytes());
//        ctx.write(encoded);
        ctx.write(msg);
        ctx.flush();

    }
}