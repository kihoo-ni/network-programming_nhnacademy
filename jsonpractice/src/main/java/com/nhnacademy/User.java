package com.nhnacademy;

public class User {
    String id;
    String name;

    

    public User(){

    }

    public User(String id, String name){
        this.id=id;
        this.name=name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
