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

        try ( // 호스트이름은 localhost, 포트번호는 1234로 서버에 연결하여 소켓생성!
                Socket socket = new Socket(host, port);

                // 서버(터미널)에서 입력.
                BufferedReader socketR = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 입출력창에 입력.
                BufferedReader terminalR = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println("서버연결 성공");

            Sender sender = new Sender(terminalR, socket);
            sender.start();
            String line;

            // exit 입력시 서버종료
            while (!(line = socketR.readLine()).equals("exit")) {
                // 서버(터미널)에서 입력된 것이 입출력창으로 출력.
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
            // 입출력창에서 exit 입력시 서버와 차단됨.
            while (!(line = terminalR.readLine()).equals("exit")) {
                // 입출력창에서 입력한 것이 서버로 출력됨
                socketW.write(line + "\n");
                socketW.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
