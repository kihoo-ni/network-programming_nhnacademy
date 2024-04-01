package com.nhnacademy;

public class Node {

    private static int nextId = 0; // 고유 아이디 기본값 0

    private int id; // 고유 식별자

    private String name; // 이름

    private Node() {
        this.id = nextId; // 다음 ID 할당
        nextId++;
    }

    public void setName(String name) { // 이름 설정
        this.name = name;
    }

    public String getName() { // 이름 가져오기
        return this.name;
    }

    public void setId(int id) { // 아이디 설정
        this.id = id;
    }

    public int getId() { // 아이디 가져오기
        return this.id;
    }

    public static Node createNode() { // 객체 생성 메소드
        return new Node();
    }

}
