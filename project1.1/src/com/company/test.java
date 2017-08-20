package com.company;

import com.company.util.aaa;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by weicong on 17-8-3.
 */
public class test {
    private static final String DEFAULT_URL = "/";

             public void run(final int port) throws Exception {
                 EventLoopGroup bossGroup = new NioEventLoopGroup();
                 EventLoopGroup workerGroup = new NioEventLoopGroup();
                 try {
                         ServerBootstrap b = new ServerBootstrap();
                         b.group(bossGroup, workerGroup)
                                 .channel(NioServerSocketChannel.class)
                                 .childHandler(new ChannelInitializer<SocketChannel>() {

                                     protected void initChannel(SocketChannel ch)
                                 throws Exception {
//                                                 ch.pipeline().addLast(new HttpRequestDecoder()); // 请求消息解码器
//                                                 ch.pipeline().addLast(new HttpObjectAggregator(65536));// 目的是将多个消息转换为单一的request或者response对象
//                                                 ch.pipeline().addLast(new HttpResponseEncoder());//响应解码器
//                                                 ch.pipeline().addLast(new ChunkedWriteHandler());//目的是支持异步大文件传输（）
                                                 // 业务逻辑
                                         ch.pipeline().addLast(new StringDecoder());
                                         ch.pipeline().addLast(new StringEncoder());
                                         ch.pipeline().addLast(new aaa());
                                             }
                     });
                         ChannelFuture future = b.bind(port).sync();
                         future.channel().closeFuture().sync();

                     } catch (Exception e) {
                         e.printStackTrace();
                     } finally {
                         bossGroup.shutdownGracefully();
                         workerGroup.shutdownGracefully();
                     }
             }

             public static void main(String[] args) throws Exception {

                 new test().run(8088);
           }
}
