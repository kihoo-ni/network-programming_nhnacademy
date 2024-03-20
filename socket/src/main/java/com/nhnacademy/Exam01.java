package com.nhnacademy;

import java.net.Socket;

public class Exam01 {
    public static void main(String[] args) {
        int startPort = Integer.parseInt(args[0]);
        int endPort = Integer.parseInt(args[1]);
        for (int port = startPort; port < endPort; port++) {
            try (Socket socket = new Socket("localhost", port)) {
                System.out.println(
                    socket.getInetAddress().getHostAddress()+":"+socket.getPort()+"-"+socket.getLocalAddress().getHostAddress()+":"+socket.getLocalPort()
                ); // Inet(원격), local(나자신)
                System.out.println(port + "가 연결되었습니다.");
            } catch (Exception ignore) {
            }
        }

    }
}
