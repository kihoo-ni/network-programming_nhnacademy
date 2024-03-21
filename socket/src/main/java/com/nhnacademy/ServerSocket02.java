package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket02 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) { // 이걸 실행하면 서버가 만들어지고 내가 localhost로 접속해야함

            while (!Thread.currentThread().isInterrupted()) {

                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                ) {

                    System.out.println("연결 : " + socket.getInetAddress().getHostAddress());
                    String line;

                    while (!(line = input.readLine()).equals("exit")) {
                        System.out.println(line);
                        socket.getOutputStream().write(line.getBytes());
                        socket.getOutputStream().write("\n".getBytes());
                        socket.getOutputStream().flush();
                    }

                } catch (Exception ignore) {
                }


            }

        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
