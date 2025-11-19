package com.helltractor.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.concurrent.TimeUnit;

public class NettyCustom {
    
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group((new NioEventLoopGroup()))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024))
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new SimpleChannelInboundHandler<String>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                    }
                });
        
        ChannelFuture connect = bootstrap.connect("localhost", 8080);
        connect.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("Connected to server successfully");
                EventLoop eventLoop = connect.channel().eventLoop();
                eventLoop.scheduleAtFixedRate(() -> {
                    String msg = "cnm" + System.currentTimeMillis() + "\n";
                    connect.channel().writeAndFlush(msg);
                }, 0, 1, TimeUnit.SECONDS);
            } else {
                System.err.println("Failed to connect to server: " + future.cause());
            }
        });
    }
    
}
