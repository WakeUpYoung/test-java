package com.wy.test.socket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServerTest {
    private int port;

    public NettyServerTest(int port) {
        this.port = port;
    }

    public void run(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 用来接收进来的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 用来处理连接
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("客户端连接：" + socketChannel.remoteAddress());
                            ChannelPipeline pipeline = socketChannel.pipeline();
//                            pipeline.addLast("frame",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            pipeline.addLast("decode",new StringDecoder());//解码器
                            pipeline.addLast("encode",new StringEncoder());
                            pipeline.addLast("handler", new NettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            System.out.println("开启服务器：" + port);
            // 绑定端口，开始接收连接
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            // 等待服务器连接关闭
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }

}
