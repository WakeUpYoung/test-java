package com.wy.test.socket.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyServerHandler extends SimpleChannelInboundHandler<String>{

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 当有客户端连接时，handlerAdded会执行,就把该客户端的通道记录下来，加入队列
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();//获得客户端通道
        //通知其他客户端有新人进入
        for (Channel channel : channels){
            if (channel != inComing)
                channel.writeAndFlush("[欢迎: " + inComing.remoteAddress() + "] 进入聊天室！\n");
        }
        channels.add(inComing);//加入队列
    }

    /**
     *断开连接
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel outComing = ctx.channel();//获得客户端通道
        //通知其他客户端有人离开
        for (Channel channel : channels){
            if (channel != outComing)
                channel.writeAndFlush(outComing.remoteAddress() + " 离开聊天室！\n");
        }

        channels.remove(outComing);

    }

    /**
     * 每当客户端有消息写入
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel inComing = channelHandlerContext.channel();
        System.out.println("服务器接收到消息：" + s);
        for (Channel channel : channels){
            if (channel != inComing){
                channel.writeAndFlush("[用户" + inComing.remoteAddress() + " 说：]" + s + "\n");
            }else {
                channel.writeAndFlush("[我说：]" + s + "\n");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel err = ctx.channel();
        System.out.println(err.remoteAddress() + "通讯异常");
        err.close();
    }

    /**
     * 当服务器监听到客户端活动时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        System.out.println("[" + inComing.remoteAddress() + "]: 在线");
    }

    /**
     * 离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel offLine = ctx.channel();
        System.out.println("[" + offLine.remoteAddress() + "]: 离线");
    }

}
