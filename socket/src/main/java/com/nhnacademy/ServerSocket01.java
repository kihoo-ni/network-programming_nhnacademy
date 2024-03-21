package com.nhnacademy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket01 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket= new ServerSocket(1234)) {
           
           while (!Thread.currentThread().isInterrupted()) {
            
               Socket socket=serverSocket.accept();
            
               System.out.println("연결 : "+socket.getInetAddress().getHostAddress());
               socket.getOutputStream().write("hello\n".getBytes());
               socket.getOutputStream().flush();
                socket.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
