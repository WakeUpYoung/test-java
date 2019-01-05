package com.wy.test.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8888);
            System.out.println("服务器启动成功");
            while (true){
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress() + "连接成功");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
