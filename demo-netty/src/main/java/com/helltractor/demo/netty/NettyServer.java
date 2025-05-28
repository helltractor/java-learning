package com.helltractor.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyServer {
    
    public static void main(String[] args) {
        Map<Channel, List<String>> db = new ConcurrentHashMap<>();
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024))
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new ResponseHandler())
                                .addLast(new DbHandler(db));
                    }
                });
        
        serverBootstrap.bind(8080).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("Server started successfully on port 8080");
            } else {
                System.err.println("Failed to start server: " + future.cause());
            }
        });
    }
    
    static class ResponseHandler extends SimpleChannelInboundHandler<String> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println(msg);
            String message = "wo " + msg + "\n";
            ctx.channel().writeAndFlush(message);
            // 将消息继续传递
            ctx.fireChannelRead(msg);
        }
        
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Channel registered: " + ctx.channel().remoteAddress());
            ctx.fireChannelRegistered();
        }
        
        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Channel unregistered: " + ctx.channel().remoteAddress());
            ctx.fireChannelUnregistered();
        }
        
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Channel active: " + ctx.channel().remoteAddress());
            ctx.fireChannelActive();
        }
    }
    
    static class DbHandler extends SimpleChannelInboundHandler<String> {
        private final Map<Channel, List<String>> db;
        
        public DbHandler(Map<Channel, List<String>> db) {
            this.db = db;
        }
        
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            List<String> messageList = db.computeIfAbsent(ctx.channel(), k -> new ArrayList<>());
            messageList.add(msg);
        }
        
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Channel registered: " + ctx.channel().remoteAddress());
        }
        
        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Channel unregistered: " + ctx.channel().remoteAddress());
        }
        
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Channel active: " + ctx.channel().remoteAddress());
        }
        
        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            List<String> messageList = db.get(ctx.channel());
            System.out.println(messageList);
        }
    }
    
}
