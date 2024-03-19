package com.nhnacademy;

public class Address {
    int code;
    String city;

    public Address(int code, String city){
        this.code=code;
        this.city=city;
    }

    public int getCode(){
        return code;
    }

    public String getCity(){
        return city;
    }
}
