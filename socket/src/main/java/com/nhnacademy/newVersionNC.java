package com.nhnacademy;

import java.io.*;
import java.net.*;
import java.util.*;

public class newVersionNC {
    public static void main(String[] args) {
        // 명령행 인수를 통해 실행 모드(서버, 클라이언트)를 결정합니다.
        String mode = args[0];

        if (mode.equals("server")) {
            runServer();
        } else if (mode.equals("client")) {
            runClient();
        } else {
            System.out.println("Invalid mode. Use 'server' or 'client'.");
        }
    }

    // 서버 실행 메서드
    private static void runServer() {
        List<Thread> clientHandlerList = new ArrayList<>();
        List<NetCat2> netcatList = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Listening on port 1234...");

            // 사용자 입력 및 출력을 처리하는 에이전트 쓰레드를 생성합니다.
            Thread inputAgent = new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    String line;
                    while ((line = input.readLine()) != null) {
                        synchronized (netcatList) {
                            for (NetCat2 netcat : netcatList) {
                                netcat.send(line + "\n");
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread outputAgent = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (netcatList) {
                        for (NetCat2 netcat : netcatList) {
                            if (!netcat.isEmptyReceiveQueue()) {
                                String line = netcat.receive();
                                System.out.println(line);
                            }
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignore) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            inputAgent.start();
            outputAgent.start();

            // 클라이언트의 연결을 수락하고 핸들링하는 메서드입니다.
            while (true) {
                Socket clientSocket = serverSocket.accept();
                NetCat2 netcat = new NetCat2(clientSocket);
                Thread thread = new Thread(netcat);
                thread.start();
                clientHandlerList.add(thread);
                netcatList.add(netcat);
                System.out.println("Client connected: " + clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 클라이언트 실행 메서드
    private static void runClient() {
        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connected to server.");

            NetCat2 netcat = new NetCat2(socket);
            Thread thread = new Thread(netcat);
            thread.start();

            // 사용자 입력 및 출력을 처리하는 에이전트 쓰레드를 생성합니다.
            Thread inputAgent = new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    String line;
                    while ((line = input.readLine()) != null) {
                        netcat.send(line + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread outputAgent = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!netcat.isEmptyReceiveQueue()) {
                        String line = netcat.receive();
                        System.out.println(line);
                    }
                }
            });

            inputAgent.start();
            outputAgent.start();
            thread.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 네트워크 통신을 담당하는 클래스입니다.
class NetCat2 implements Runnable {
    private final Socket socket;
    private final Queue<String> receiveQueue = new LinkedList<>();
    private final Queue<String> sendQueue = new LinkedList<>();

    // 생성자로 소켓을 초기화합니다.
    public NetCat2(Socket socket) {
        this.socket = socket;
    }

    // 메시지를 송신하는 메서드입니다.
    public void send(String message) {
        synchronized (sendQueue) {
            sendQueue.add(message);
        }
    }

    // 수신 큐가 비어있는지 확인하는 메서드입니다.
    public boolean isEmptyReceiveQueue() {
        synchronized (receiveQueue) {
            return receiveQueue.isEmpty();
        }
    }

    // 메시지를 수신하는 메서드입니다.
    public String receive() {
        synchronized (receiveQueue) {
            return receiveQueue.poll();
        }
    }

    // 쓰레드가 실행하는 메인 로직입니다.
    @Override
    public void run() {
        try (BufferedReader inputRemote = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter outputRemote = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            // 수신 및 송신을 처리하는 쓰레드를 생성하고 실행합니다.
            Thread receiver = new Thread(() -> {
                try {
                    String line;
                    while ((line = inputRemote.readLine()) != null) {
                        synchronized (receiveQueue) {
                            receiveQueue.add(line);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread sender = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        synchronized (sendQueue) {
                            if (!sendQueue.isEmpty()) {
                                outputRemote.write(sendQueue.poll());
                                outputRemote.flush();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            receiver.start();
            sender.start();

            receiver.join();
            sender.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
