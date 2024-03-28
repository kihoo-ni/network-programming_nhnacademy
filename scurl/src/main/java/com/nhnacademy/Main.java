package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        String version = "HTTP/1.1";
        String command = "GET";
        String location = "";
        String customHeader = "";
        String jsonString = "";
        String contentType = "application/json";

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

            if (line.getArgs().length > 0) {
                String host = line.getArgs()[0];

                URL url = new URL(host);
                System.out.println("호스트 : " + url.getHost());

                location = url.getPath();

                Socket socket = new Socket(url.getHost(), port);

                PrintStream writer = new PrintStream(socket.getOutputStream());
                System.out.println("커맨드 : " + command);
                System.out.println("로케이션 : " + location);
                System.out.println("버전 : " + version);
                writer.printf("%s %s %s\r\n", command, location, version);
                writer.printf("Host: %s\r\n", url.getHost());
                writer.printf("User-Agent: curl/7.79.1\r\n");
                writer.printf("Accept: */*\r\n");
                // writer.printf("Content-Type: %s\r\n", contentType);

                if (line.hasOption("H")) {
                    customHeader = line.getOptionValue("H");
                    writer.printf("%s\r\n", customHeader);

                }
                if (line.hasOption("d")) {
                    jsonString = line.getOptionValue("d");

                }

                writer.printf("Content-Length: %d\r\n", jsonString.length());

                writer.printf("\r\n");

                writer.printf("%s\r\n", jsonString);

                if (line.hasOption("v")) {
                    System.out.printf(">> %s %s %s\r\n", command, location, version);
                    System.out.printf(">> Host: %s\r\n", url.getHost());
                    System.out.printf(">> %s\r\n", customHeader);
                    System.out.printf(">> Content-Length: %d\r\n", jsonString.length());
                    System.out.printf(">> \r\n");
                }
                Thread receiver = new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        String inputLine;

                        if (line.hasOption("v")) {

                            boolean headerComplete = false;
                            while (!headerComplete) {
                                String lineFromServer = reader.readLine();
                                System.out.println("<< " + lineFromServer);

                                if (lineFromServer.trim().isEmpty()) {
                                    headerComplete = true;
                                }
                            }

                            while ((inputLine = reader.readLine()) != null) {
                                System.out.println(inputLine);
                            }

                        } else {

                            boolean headerComplete = false;
                            while (!headerComplete) {
                                String lineFromServer = reader.readLine();
                                if (lineFromServer.trim().isEmpty()) {
                                    headerComplete = true;
                                }
                            }

                            while ((inputLine = reader.readLine()) != null) {
                                System.out.println(inputLine);
                            }
                        }

                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });

                receiver.start();

                receiver.join();

                socket.close();
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
