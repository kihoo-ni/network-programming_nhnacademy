package com.nhnacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Exam06 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("서버 연결 성공");

            Thread thread = new Thread(() -> {

                try {

                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String line;
                    while (!(line = input.readLine()).equals("exit")) {
                        System.out.println(line);
                    }

                } catch (Exception e) {
                }

            });
            thread.start();

            while (!Thread.currentThread().interrupted()) {
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                while (!(line = reader.readLine()).equals("exit")) {
                    socket.getOutputStream().write(line.getBytes());
                    socket.getOutputStream().write("\n".getBytes());
                }

            }
        } catch (Exception e) {
            System.out.println("서버 연결 실패");
        }
    }


}
