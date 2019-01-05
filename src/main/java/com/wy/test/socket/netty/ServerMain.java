package com.wy.test.socket.netty;

public class ServerMain {
    public static void main(String[] args) {
        new NettyServerTest(8888).run();
    }
}
