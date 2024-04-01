package com.nhnacademy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private static int nextId = 0; // 고유 아이디 기본값 0

    private int id; // 메시지 고유 식별자

    private String createTime; // 메시지 생성 시간

    private boolean priority; // 우선순위

    private Data data; // 메시지 데이터 유형

    public boolean hasPriority() { // 우선순위를 가지는지 확인
        return this.priority;
    }

    public void setPriority(boolean priority) { // 우선순위 설정
        this.priority = priority;
    }

    public int getId() { // 아이디 가져오기
        return this.id;
    }

    public void setId(int id) { // 아이디 설정하기
        this.id = id;
    }

    public String getCreateTime() { // 메시지 생성시간 가져오기
        return this.createTime;
    }

    public void setCreateTime(String createTime) { // 메시지 생성시간 설정하기
        this.createTime = createTime;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Message() { // 메시지 인스턴스 생성을 막기위한 private 생성자
        this.id = nextId; // 다음 ID 할당
        nextId++;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd E HH:mm");
        createTime = now.format(dateTimeFormatter);
    }

    public static Message createMessage() { // 객체 생성 메소드
        return new Message();
    }

}
