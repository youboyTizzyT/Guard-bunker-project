package com.company.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by weicong on 17-8-8.
 */
public class aaa extends ChannelInboundHandlerAdapter{
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx);
        System.out.println(msg);
        ctx.writeAndFlush("1593574561|56456");
    }
}
