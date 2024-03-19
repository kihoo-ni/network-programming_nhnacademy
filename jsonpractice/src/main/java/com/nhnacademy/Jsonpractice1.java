package com.nhnacademy;

import org.json.JSONObject;

public class Jsonpractice1 {
    public static void main(String[] args) {
        JSONObject addressObject = new JSONObject();
        addressObject.put("code", 13487);
        addressObject.put("city", "Seongnam");

        // 전체 JSON 객체 생성
        JSONObject dataObject = new JSONObject();
        dataObject.put("name", "nhn");
        dataObject.put("address", addressObject);

        // 결과 출력
        System.out.println(dataObject.toString());
    }
    
    
    
}
