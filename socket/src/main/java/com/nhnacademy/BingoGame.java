package com.nhnacademy;

import java.util.Scanner;

public class BingoGame {
    public static void main(String[] args) {

        try (
                Scanner scanner = new Scanner(System.in);

        ) {
            System.out.println("빙고게임 시작!");
            System.out.println("----------------------------------------------------------");
            System.out.println("빙고 줄 수 선택");
            System.out.println("----------------------------------------------------------");

            int bingoLineNumber = scanner.nextInt();
            System.out.println("----------");
            System.out.println(bingoLineNumber + "줄 짜리 빙고를 생성");
            System.out.println("----------------------------------------------------------");
            System.out.println("유저가 빙고판에 체크 할 경우 숫자에 O, 컴퓨터가 체크 할 경우 숫자에 X로 표시됨");

            // 나의 빙고클래스 객체생성
            Bingo myBingo = new Bingo(bingoLineNumber);

            // 유저 빙고에 숫자값 대입
            myBingo.insertBingoNumber();

            System.out.println("------------");

            // 빙고 보여주기
            System.out.println(myBingo.showMeTheBingo());

            // 컴퓨터 빙고클래스 객체생성
            Bingo computerBingo = new Bingo(bingoLineNumber);

            // 컴퓨터 빙고에 숫자값 대입
            computerBingo.insertComputerBingoNumber();

            System.out.println("------------");

            // 빙고 보여주기
            System.out.println(computerBingo.showMeTheBingo());


            //빙고 숫자판에 고르는 숫자의 중복을 방지하기위해 배열초기화
            Bingo.checkNUmArrayInputNum();

            // 유저 빙고 숫자고르기+ 컴퓨터 빙고도 숫자 골라짐 if 컴퓨터 판에 해당 숫자 없을 경우 패스
            myBingo.checkBingoNumber(computerBingo, false);

            System.out.println("_____");
            System.out.println(myBingo.showMeTheBingo());
            System.out.println("_____");
            System.out.println(computerBingo.showMeTheBingo());
            System.out.println("_____");

            // 컴퓨터 빙고 숫자고르기 + 유저빙고도 숫자 골라짐 if 컴퓨터 판에 해당 숫자 없을 경우 패스
            computerBingo.checkBingoNumber(myBingo, true);

            System.out.println(myBingo.showMeTheBingo());
            System.out.println("_____");
            System.out.println(computerBingo.showMeTheBingo());
            System.out.println("_____");

            // 빙고 카운트 체크
            myBingo.detectBingo("O");

            computerBingo.detectBingo("X");

            // 모든 선수의 빙고판이 다 채워졌는지 확인하고 bingo 카운트해서 가장 많이 빙고 가지고 있으면 승리
            if (myBingo.returnBingoCount() > computerBingo.returnBingoCount()) {
                System.out.println("++++++++++");
                System.out.println("나의 승리!");
                System.out.println("++++++++++");
                return;
            } else {
                System.out.println("++++++++++");
                System.out.println("컴퓨터 승리! ");
                System.out.println("++++++++++");
                return;

            }

        } catch (Exception ignore) {
        }

    }
}
