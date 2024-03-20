package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Exam06_1 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        try (
                Socket socket = new Socket(host, port);
                BufferedReader socketR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader terminalR = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println("서버연결 성공");

            
            Sender sender = new Sender(terminalR, socket);
            sender.start();
            String line;

            while (!(line = socketR.readLine()).equals("exit")) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("서버연결 실패");
        }

    }

}

class Sender extends Thread {

    BufferedReader terminalR;
    Socket socket;

    public Sender(BufferedReader terminalR, Socket socket) {
        this.terminalR = terminalR;
        this.socket = socket;
    }

    @Override
    public void run() {

        try (BufferedWriter socketW = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
            String line;
            while (!(line = terminalR.readLine()).equals("exit")) {
                socketW.write(line+"\n");
                socketW.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
