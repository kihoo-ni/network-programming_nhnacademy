package com.nhnacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActiveNode {
    Logger logger = LogManager.getLogger();
    Node node;

    // 상태 enum클래스로 표현
    public enum State {
        START, INITIALIZED, PERFORMING, IDLE, FINALIZED, TERMINATED
    }

    // 상태 필드
    private State currentState;

    // 생성자
    private ActiveNode() {
        node = Node.createNode();
        this.currentState = State.START;
    }

    // 생성 메소드
    public static ActiveNode createActiveNode() {
        return new ActiveNode();
    }

    public State getCurrentState() {
        return currentState;
    }

    public void start() {
        if (currentState == State.START) {
            currentState = State.INITIALIZED;
            logger.trace("ActiveNode started and initialized.");
        } else {
            logger.trace("ActiveNode is already started.");
        }
    }

    public void perform() {
        if (currentState == State.INITIALIZED || currentState == State.IDLE) {
            currentState = State.PERFORMING;
            logger.trace("ActiveNode performing.");
        } else {
            logger.trace("ActiveNode cannot perform in current state.");
        }
    }

    public void idle() {
        if (currentState == State.PERFORMING) {
            currentState = State.IDLE;
            logger.trace("ActiveNode idling.");
        } else {
            logger.trace("ActiveNode cannot idle in current state.");
        }
    }

    public void finalizeNode() {
        if (currentState == State.PERFORMING || currentState == State.IDLE) {
            currentState = State.FINALIZED;
            logger.trace("ActiveNode finalized.");
        } else {
            logger.trace("ActiveNode cannot be finalized in current state.");
        }
    }

    public void terminate() {
        if (currentState != State.TERMINATED) {
            currentState = State.TERMINATED;
            logger.trace("ActiveNode terminated.");
        } else {
            logger.trace("ActiveNode is already terminated.");
        }
    }
}
