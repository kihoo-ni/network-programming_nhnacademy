package com.nhnacademy;

import org.json.JSONObject;

public class Jsonpractice5 {
    
    public static void main(String[] args) {
        
        JSONObject object=new JSONObject();
        object.put("k","ㄱ");
        object.put("d", 1);



        // 타입값 없을때 디폴트 불러옴 
        System.out.println("optInt test : "+object.optInt("k",100));
    }
    

}
