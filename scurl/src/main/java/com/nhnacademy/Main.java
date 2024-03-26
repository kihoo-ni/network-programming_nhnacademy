package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        String version = "HTTP/1.1";
        String command = "GET";
        String location = "/get";
        int port = 80;

        options.addOption("X", true, "사용할 method를 지정함. 지정되지 않을 경우, 기본값은 GET");
        options.addOption("H", true, "임의의 헤더를 서버로 전송함.");
        options.addOption("v", false, "verbose, 요청, 응답 헤더를 출력함");
        options.addOption("d", true, "POST, PUT 등에 데이터를 전송함");
        options.addOption("L", false, "서버의 응답이 30x 계열이면 다음 응답을 따라 간다.");
        options.addOption("F", true, "multipart/form-data를 구성하여 전송함. content 부분에 @filename을 사용할 수 있다.");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);

            if (line.hasOption("X")) {
                command = line.getOptionValue("X");
            }

            if (line.hasOption("v")) {

            }

            if (line.getArgs().length > 0) {
                String host = line.getArgs()[0];

                Socket socket = new Socket(host, port);

                PrintStream writer = new PrintStream(socket.getOutputStream());

                writer.printf("%s %s %s\r\n", command, location, version);
                writer.printf("Host: %s\r\n", host);
                writer.printf("\r\n");

                Thread receiver = new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        String inputLine;
                        while ((inputLine = reader.readLine()) != null) {
                            System.out.println(inputLine);
                        }
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });

                receiver.start();

                receiver.join();
            } else {
                System.err.println("URL이 필요합니다.");
            }
        } catch (IOException | ParseException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
