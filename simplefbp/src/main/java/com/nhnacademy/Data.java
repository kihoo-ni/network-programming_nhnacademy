package com.nhnacademy;

public class Data {
    private String dataContent; // 데이터 내용

    public String getDataContent() { // 데이터 내용 가져오기
        return this.dataContent;
    }

    public void setDataContent(String dataContent) { // 데이터내용 설정하기
        this.dataContent = dataContent;
    }

    public Data() { // 기본 생성자

    }

    public Data(String dataContent) { // 데이터 생성자
        this.dataContent = dataContent;
    }
}
