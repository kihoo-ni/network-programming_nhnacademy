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
        Option addId = new Option("i", "id", true, "select data id");
        Option addName = new Option("n", "name", true, "select name");
        Option addList = new Option("l", "list", false, "list data");

        // 옵션 추가
        options.addOption(helpOption);
        options.addOption(dbOption);
        options.addOption(addOption);
        options.addOption(addId);
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
            }
            // 추가 옵션
            if (commandLine.hasOption(addOption.getOpt())) {
                JSONObject userObject = new JSONObject();
                // 아이디
                if (commandLine.hasOption(addId.getOpt())) {
                    System.out.println("아이디 추가!");
                    userObject.put("ID", commandLine.getOptionValue(addId.getOpt()));
                } else {
                    throw new NullPointerException();
                }

                // 이름
                if (commandLine.hasOption(addName.getOpt())) {
                    System.out.println("이름 추가");
                    userObject.put("NAME", commandLine.getOptionValue(addName.getOpt()));
                    userArray.put(userObject);
                } else {
                    throw new NullPointerException();
                }

                // 저장옵션
                if (commandLine.hasOption(dbOption.getOpt())) {

                    // 읽기 옵션
                    System.out.println("파일읽어보자!");
                    String filePath = commandLine.getOptionValue(dbOption.getOpt());

                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(userObject.toString());
                        System.out.println("파일저장 성공");
                    } catch (Exception e) {
                        System.out.println("파일저장 실패 : " + e.getMessage());
                    }
                }
            }

            // 리스트 열거옵션
            if (commandLine.hasOption(addList.getOpt())) {
                System.out.println("열거해보기");
                // 읽기 옵션
                if (commandLine.hasOption(dbOption.getOpt())) {
                    System.out.println("파일읽어보자!");
                    String filePath = commandLine.getOptionValue(dbOption.getOpt());

                    try {
                        String jsonData = Files.readString(Paths.get(filePath));
                        JSONObject newObject=new JSONObject(jsonData);
                        
                    
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("db 읽기 실패");
                    }
                }

                System.out.println("ID      NAME");

                for (int i = 0; i < userArray.length(); i++) {
                    JSONObject userObject = userArray.getJSONObject(i);
                    if (userObject.getString("ID").equals(commandLine.getOptionValue(addId.getOpt()))) {
                        System.out.println(userObject.getString("ID") + "      " + userObject.getString("NAME"));
                    }
                }

            }

        } catch (

        ParseException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }
}