package com.nhnacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Exam03 {
    public static void main(String[] args) {

        String host = "localhost";
        int port = 1234;
        // nc -l 1234

        try (Socket socket = new Socket(host, port); ) {
            System.out.println("서버 연결완료");

            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!(line=input.readLine()).equals("exit")) {
                System.out.println(line);
            }
        } catch (Exception ignore) {
            System.err.println(host + ":" + port + "에 연결할 수 없습니다.");
        }
    }

}
