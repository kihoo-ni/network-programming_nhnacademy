package com.nhnacademy;

import org.json.JSONTokener;

public class Jsonpractice7 {
    public static void main(String[] args) {
        JSONTokener tokener = new JSONTokener("{\"name\":\"nhn\"}");

        while (!tokener.end()) {
            Object object = tokener.nextValue();
            System.out.println(object.getClass().getTypeName()+" : "+object);
        }
    }

}
