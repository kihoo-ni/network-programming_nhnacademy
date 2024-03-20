package com.nhnacademy;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        // Json 배열 선언
        JSONArray userArray = new JSONArray();

        // 옵션 생성
        // 실제 실행할떄는 - 붙혀서 하자! 
        Options options = new Options();
        Option helpOption = new Option("h", "help", false, "Help");
        Option dbOption = new Option("f", "dbfile", true, "save DB file");
        Option addOption = new Option("a", "add", false, "add Data");
        Option addType = new Option("t", "type", true, "select data type");
        Option addName = new Option("n", "name", true, "select name");
        Option addList = new Option("l", "list", false, "list data");

        // 옵션 추가
        options.addOption(helpOption);
        options.addOption(dbOption);
        options.addOption(addOption);
        options.addOption(addType);
        options.addOption(addName);
        options.addOption(addList);

        CommandLineParser parser = new DefaultParser();
        try {

            // 명령어 파싱
            CommandLine commandLine = parser.parse(options, args);

            // 헬프 옵션
            if (commandLine.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("recoder", options);

                // 저장 옵션
            } else if (commandLine.hasOption(dbOption.getOpt())) {

                String filePath = commandLine.getOptionValue(dbOption.getOpt());

                    try {
                        String jsonData = Files.readString(Paths.get(filePath));
                     userArray.putAll(jsonData);
                        System.out.println(jsonData);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("db 읽기 실패");
                    }

                try (FileWriter writer = new FileWriter(filePath)) {
                    writer.write(userArray.toString());
                    System.out.println("파일저장 성공");
                } catch (Exception e) {
                    System.out.println("파일저장 실패 : " + e.getMessage());
                }

                // 추가 옵션
            } else if (commandLine.hasOption(addOption.getOpt())) {
                JSONObject userObject = new JSONObject();
                if (commandLine.hasOption(addType.getOpt())) {
                    userObject.put("TYPE", commandLine.getOptionValue(addType.getOpt()));
                } else {
                    throw new NullPointerException();
                }
                if (commandLine.hasOption(addName.getOpt())) {
                    userObject.put("NAME", commandLine.getOptionValue(addName.getOpt()));
                } else {
                    throw new NullPointerException();
                }
                userArray.put(userObject);

                // 리스트 열거옵션
            } else if (commandLine.hasOption(addList.getOpt())) {

                System.out.println("ID      NAME");
                if (commandLine.hasOption(addType.getOpt())) {

                    for (int i = 0; i < userArray.length(); i++) {
                        JSONObject userObject = userArray.getJSONObject(i);
                        if (userObject.getString("TYPE").equals(commandLine.getOptionValue(addType.getOpt()))) {
                            System.out.println(userObject.getString("TYPE") + "      " + userObject.getString("NAME"));
                        }
                    }
                }
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }
}