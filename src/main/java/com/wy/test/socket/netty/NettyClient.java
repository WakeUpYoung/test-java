package com.wy.test.socket.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NettyClient {

    private String host;
    private int port;


    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("frame",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            pipeline.addLast("decode",new StringDecoder());//解码器
                            pipeline.addLast("encode",new StringEncoder());
                            pipeline.addLast("handler", new NettyClientHandler());
                        }
                    });
            Channel channel = bootstrap.connect(host, port).sync().channel();
            System.out.println("连接到服务器");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                channel.writeAndFlush(input.readLine());
            }
        }catch (InterruptedException | IOException e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) {
        try {
            new NettyClient("localhost", 8888).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
