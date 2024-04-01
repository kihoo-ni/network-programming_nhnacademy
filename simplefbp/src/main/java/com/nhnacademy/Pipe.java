package com.nhnacademy;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pipe {
    private static int nextId = 0; // 다음에 사용될 ID를 저장하는 클래스 변수

    private int id; // Pipe의 고유한 ID
    private Queue<Message> normalQueue; // 우선순위가 없는 일반 큐
    private Queue<Message> priorityQueue; // 우선순위가 있는 큐
    private int capacity; // 메시지 저장 용량

    Logger logger = LogManager.getLogger();

    // 기본 생성자
    private Pipe() {
        this.id = nextId; // 다음 ID 할당
        nextId++;
        this.normalQueue = new LinkedList<>(); // 일반 큐 초기화
        this.priorityQueue = new LinkedList<>(); // 우선순위 큐 초기화
    }

    // 조건 생성자
    private Pipe(int capacity) {
        this.id = nextId; // 다음 ID 할당
        nextId++;
        this.normalQueue = new LinkedList<>(); // 일반 큐 초기화
        this.priorityQueue = new LinkedList<>(); // 우선순위 큐 초기화
        this.capacity = capacity; // 용량 설정
    }

    // Pipe 객체 생성 메서드
    public static Pipe createPipe(int capacity) {
        return new Pipe(capacity);
    }

    // Pipe 기본 생성자 객체 생성 메서드
    public static Pipe createPipe() {
        return new Pipe();
    }

    // ID 반환
    public int getId() {
        return this.id;
    }

    // capacity 반환
    public int getCapacity() {
        return this.capacity;
    }

    // 메시지 추가
    public void addMessage(Message message) {
        if (message.hasPriority()) { // 메시지가 우선순위를 가진 경우
            if (priorityQueue.size() < capacity) { // 용량을 초과하지 않는 경우에만 추가
                priorityQueue.add(message);
            } else {
                logger.trace("Priority Queue capacity exceeded. Message not added.");
            }
        } else { // 우선순위가 없는 경우
            if (normalQueue.size() < capacity) { // 용량을 초과하지 않는 경우에만 추가
                normalQueue.add(message);
            } else {
                logger.trace("Normal Queue capacity exceeded. Message not added.");
            }
        }
    }

    // 메시지 전체 반환
    public Queue<Message> getNormalMessages() {
        return this.normalQueue;
    }

    public Queue<Message> getPriorityMessages() {
        return this.priorityQueue;
    }
}
