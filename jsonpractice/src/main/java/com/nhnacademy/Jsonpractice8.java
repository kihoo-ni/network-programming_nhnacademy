package com.nhnacademy;

import java.io.Console;

import org.json.JSONArray;
import org.json.JSONObject;

public class Jsonpractice8 {
    public static void main(String[] args) {
        JSONArray userArray = new JSONArray();

        Console console = System.console();

        while (true) {
            System.out.println("command : ");
            String line = console.readLine();
            if (line.equals("quit")) {
                break;
            }

            String[] str = line.split(" ");

            if (str[0].equals("user")) {
                if (str[1].equals("add")) {
                    JSONObject userObject = new JSONObject();
                    userObject.put("ID", str[2]);
                    userObject.put("NAME", str[3]);
                    userArray.put(userObject);
                    System.out.println("사용자 " + str[3] + "가 추가 되었습니다.");
                } else if (str[1].equals("del")) {

                    for (int i = 0; i < userArray.length(); i++) {
                        JSONObject userObject = userArray.getJSONObject(i);
                        if (userObject.getString("ID").equals(str[2])) {
                            userArray.remove(i);
                            System.out.println("사용자 " + str[2] + "가 삭제 되었습니다.");
                            break;
                        }
                    }
                } else if (str[1].equals("list")) {
                    System.out.println("ID      NAME");
                    for (int i = 0; i < userArray.length(); i++) {
                        JSONObject userObject = userArray.getJSONObject(i);
                        System.out.println(
                                userObject.getString("ID") + "      " + userObject.getString("NAME"));
                    }
                }
            }

            if (str[0].equals("item")) {
                Item item = new Item(str[1], str[2], Integer.parseInt(str[3]), Integer.parseInt(str[4]),
                        Integer.parseInt(str[5]), Integer.parseInt(str[6]), Integer.parseInt(str[7]));
                JSONObject itemObject = new JSONObject();
                itemObject.put(str[0], item);

            }

            if (str[0].equals("record")) {
                Record record = new Record(Integer.parseInt(str[1]), Integer.parseInt(str[2]));
                JSONObject recordObject = new JSONObject();
                recordObject.put(str[0], record);

            }

            System.out.println("input command : " + line);
        }

    }

}
