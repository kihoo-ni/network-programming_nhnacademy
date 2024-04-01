package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TimerNode implements Producer, Runnable {

    private Pipe outputPipe;
    private long interval;
    Data data;

    @Override
    public void messageOutput(Pipe pipe) {
        this.outputPipe = pipe;
    }

    // 콘솔 입력을 받아 메시지로 전달하는 메서드
    public synchronized void receiveInput() {

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in));) {
            String line;
            while ((line = input.readLine()) != null) {
                // 입력 받은 데이터를 메시지로 생성하여 출력 파이프에 전송

                Message message = Message.createMessage();
                data.setDataContent(line);
                message.setData(data);
                outputPipe.addMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {
            Thread.sleep(interval);
            receiveInput();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
