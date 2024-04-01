package com.nhnacademy;

import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TerminalOutNode 클래스 구현
public class TerminalOutNode implements Consumer {
    Logger logger = LogManager.getLogger();

    private Pipe inputPipe;

    @Override
    public void messageInput(Pipe pipe) {
        this.inputPipe = pipe;
    }

    // 수신된 메시지를 표준 출력으로 내보내는 메서드
    public void sendMessageToTerminal() {
        // 입력 파이프에서 메시지를 받아와 표준 출력에 출력하는 로직
        Queue<Message> messages = inputPipe.getNormalMessages();
        for (Message message : messages) {
            // 메시지 출력
            logger.trace("Message: {}", message.getData().getDataContent());
        }
    }
}
