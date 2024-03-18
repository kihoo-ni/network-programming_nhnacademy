package com.nhnacademy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        for (String s : args) {
            String regex = "\\b[+-]?(0|[1-9][0-9]{0,9})\\b";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                int value=Integer.parseInt(s);
                System.out.println("int : "+value);
            } else {
                System.out.println("String : "+s);
            }
        }

        System.out.println("Hello world!");
    }
}