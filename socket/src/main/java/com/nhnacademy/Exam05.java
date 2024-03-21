package com.nhnacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Exam05 {
    public static void main(String[] args) { // 내가 nc -l 1234 서버 만들어서 사용!
        String host = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(host, port);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            System.out.println("서버 연결 성공");
            String line;
            while (!(line = input.readLine()).equals("exit")) {
                socket.getOutputStream().write(line.getBytes());
                socket.getOutputStream().write("\n".getBytes());
            }
            input.close();
        } catch (Exception e) {
            System.out.println("서버 연결 실패");
        }
    }
}
