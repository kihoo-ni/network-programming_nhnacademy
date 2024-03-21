package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class Snc {
    public static void main(String[] args) {

        Options options = new Options();

        options.addOption("h", "help", false, "usage this options");
        options.addOption("l", "listen", true, "server listen port");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            // help 명령어
            if (commandLine.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("help", options);

                // l 명령어를 통해 서버생성 서버모드 - cmd창에서 클라이언트로 접속해줘야함.
            } else if (commandLine.hasOption("l")) {
                String listenPort = commandLine.getOptionValue("l");
                System.out.println("Listen port: " + listenPort);

                // 명령줄 인자 (port) 처리
                String remainingArgs = commandLine.getOptionValue("l");
                if (!remainingArgs.equals(null)) {
                    String port = remainingArgs;
                    System.out.println("Port: " + port);

                    try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
                            Socket socket = serverSocket.accept();
                            BufferedWriter clientW = new BufferedWriter(
                                    new OutputStreamWriter(socket.getOutputStream()));

                            BufferedReader serverR = new BufferedReader(new InputStreamReader(System.in));

                    ) {
                        System.out.println("서버 오픈 성공! 클라이언트와 연결 완료!");

                        Receiver receiver = new Receiver(socket);
                        receiver.start();

                        String line;
                        try {
                            while (!(line = serverR.readLine()).equals(null)) {
                                clientW.write(line + "\n");
                                clientW.flush();
                            }

                        } catch (IOException e) {
                            System.err.println("서버와의 연결이 끊겼습니다 : " + e.getMessage());
                        }

                    } catch (IOException e) {

                        System.err.println(e.getMessage());
                    }

                } else {
                    System.out.println("port are required.");
                }
            } else {
                // 클라이언트 모드- cmd창에서 서버 켜줘야함
                // 명령줄 인자 (hostname, port) 처리
                String[] remainingArgs = commandLine.getArgs();
                if (remainingArgs.length >= 2) {
                    String hostname = remainingArgs[0];
                    String port = remainingArgs[1];
                    System.out.println("Hostname: " + hostname);
                    System.out.println("Port: " + port);

                    try ( // 소켓 연결
                            Socket socket = new Socket(hostname, Integer.parseInt(port));

                            BufferedReader clientR = new BufferedReader(new InputStreamReader(System.in));

                            BufferedWriter serverW = new BufferedWriter(
                                    new OutputStreamWriter(socket.getOutputStream()));

                    ) {
                        System.out.println("서버연결 성공!");

                        Receiver receiver = new Receiver(socket);
                        receiver.start();

                        String line;

                        try {

                            // 클라이언트가 서버로 출력
                            while (!(line = clientR.readLine()).equals(null)) {
                                serverW.write(line + "\n");
                                serverW.flush();
                            }
                        } catch (IOException e) {
                            System.err.println("클라이언트와 연결이 끊겼습니다. : " + e.getMessage());
                        }

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }

                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}

class Receiver extends Thread {
    Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (
                BufferedReader serverR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter clientW = new BufferedWriter(new OutputStreamWriter(System.out));) {
            String line;

            // 서버가 클라이언트에게 출력
            while (!(line = serverR.readLine()).equals(null)) {
                clientW.write(line + "\n");
                clientW.flush();

            }

        } catch (Exception e) {
            System.err.println("연결이 끊겼습니다. : " + e.getMessage());
        }
    }
}
