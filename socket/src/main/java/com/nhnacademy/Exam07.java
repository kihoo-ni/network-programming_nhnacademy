package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Exam07 {
    public static void main(String[] args) {

        String host = "localhost";
        int port = 1234;

        try (
                Socket socket = new Socket(host, port);
                BufferedReader terminalR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader socketR = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter terminalW = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedWriter socketW = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            System.out.println("연결성공");
            String line;
            while((line=socketR.readLine())!=null){
                socketW.write(line+"\n");
                socketW.flush();
                line = terminalR.readLine();
                terminalW.write(line+"\n");
                terminalW.flush();

            }


        } catch (Exception ignore) {
            System.out.println("연결실패");
        }
    }

}
