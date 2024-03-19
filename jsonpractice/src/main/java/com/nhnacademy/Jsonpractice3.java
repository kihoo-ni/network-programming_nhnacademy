package com.nhnacademy;

import org.json.JSONObject;

public class Jsonpractice3 {
    public static void main(String[] args) {
        
        Address address= new Address(13487, "Seongnam");
        Person person= new Person("kihoon", address);

        JSONObject object =new JSONObject(person);
        System.out.println(object);
    }
}
