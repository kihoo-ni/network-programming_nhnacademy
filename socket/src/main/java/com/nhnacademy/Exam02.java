package com.nhnacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Exam02 {
    public static void main(String[] args) {

        String host="localhost";
        int port=1234;
        //nc -l 1234

        try (Socket socket = new Socket(host, port);) {
                System.out.println("서버 연결완료");
            while (true) {
                BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

                System.out.println("입력하세요 : ");

                String cmd=reader.readLine();

                if(cmd.equals("exit")){
                    System.exit(0);
                }
                    socket.getOutputStream().write(cmd.getBytes());
                    socket.getOutputStream().write("\n".getBytes());

            }    
            } catch (Exception ignore) {
                System.err.println(host+":"+port+"에 연결할 수 없습니다.");
            }
        }

    }
