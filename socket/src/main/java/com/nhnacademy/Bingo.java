package com.nhnacademy;

import java.util.Scanner;

public class Bingo {
    String[][] bingo;
    int bingoCount;
    public static int bingoLineNumber;
    public static String[] checkNumArray;
    Scanner scanner = new Scanner(System.in);
    public static boolean stopCheckBingo = true;

    public Bingo(int bingoLineNumber) {
        this.bingoLineNumber = bingoLineNumber;
        bingo = new String[bingoLineNumber][bingoLineNumber];

    }

    public static void checkNUmArrayInputNum() {
        // 빙고 숫자판에 고르는 숫자의 중복을 방지하기위해 배열초기화
        checkNumArray = new String[bingoLineNumber * bingoLineNumber];
        for (int i = 0; i < bingoLineNumber * bingoLineNumber; i++) {

            checkNumArray[i] = Integer.toString(i + 1);
        }
    }

    public void insertBingoNumber() {
        System.out.println("----------------------------------------------------------");
        System.out.println("빙고판에 숫자를 입력합니다. 숫자는 좌우 위아래 순으로 입력");
        System.out.println("----------------------------------------------------------");
        System.out.println("숫자는 본인이 선택한 빙고 " + bingoLineNumber + "줄수 기준으로 1부터 " + bingoLineNumber * bingoLineNumber
                + " 이하의 숫자로 입력");

        for (int row = 0; row < bingoLineNumber; row++) {
            for (int col = 0; col < bingoLineNumber; col++) {
                if (col == bingoLineNumber - 1) {
                    System.out.println("열의 마지막 숫자를 입력해주세요");
                    System.out.println("____");
                } else {

                    System.out.println("숫자를 입력해주세요");
                    System.out.println("___");
                }

                int num = scanner.nextInt();
                bingo[row][col] = Integer.toString(num);
            }
        }

    }

    public void insertComputerBingoNumber() {
        int lastnum = bingoLineNumber * bingoLineNumber;

        String[] selectNumbers = new String[lastnum];

        for (int i = 0; i < lastnum; i++) {
            selectNumbers[i] = Integer.toString(i + 1);
        }

        for (int row = 0; row < bingoLineNumber; row++) {
            for (int col = 0; col < bingoLineNumber; col++) {

                int num = (int) (Math.random() * lastnum);
                String selectNum = selectNumbers[num];
                if (selectNum == null) {
                    col--;
                    continue;
                }

                selectNumbers[num] = null;
                bingo[row][col] = selectNum;

            }
        }

    }

    public String showMeTheBingo() {
        String bingoArray = new String();

        for (int row = 0; row < bingoLineNumber; row++) {
            for (int col = 0; col < bingoLineNumber; col++) {
                if (col == bingoLineNumber - 1) {

                    bingoArray += bingo[row][col] + "\n";
                } else {

                    bingoArray += bingo[row][col] + " ";
                }
            }
        }
        return bingoArray;
    }

    public void checkBingoNumber(Bingo otherBingo, boolean computer) {
        boolean isComputer = computer;

        if (!isComputer) {

            System.out.println("숫자를 고르세요 : ");
            int pickNum = scanner.nextInt();
            scanner.nextLine();
            String pickNumToStr = Integer.toString(pickNum);
            checkNumArray[pickNum - 1] = null;

            loop: for (int i = 0; i < bingoLineNumber; i++) {
                for (int j = 0; j < bingoLineNumber; j++) {
                    if (pickNumToStr.equals(bingo[i][j]) && (!bingo[i][j].equals("O"))
                            && (!bingo[i][j].equals("X"))) {

                        bingo[i][j] = "O";
                        break loop;
                    }
                }
            }
            loop: for (int i = 0; i < bingoLineNumber; i++) {
                for (int j = 0; j < bingoLineNumber; j++) {
                    if (pickNumToStr.equals(otherBingo.bingo[i][j]) && (!otherBingo.bingo[i][j].equals("O"))
                            && (!otherBingo.bingo[i][j].equals("X"))) {
                        otherBingo.bingo[i][j] = "O";
                        break loop;
                    }
                }
            }

        } else {
            int randomComNum = (int) (Math.random() * (bingoLineNumber * bingoLineNumber)) + 1;
            String randomComStr = Integer.toString(randomComNum);

            loop: for (int i = 0; i < bingoLineNumber * bingoLineNumber; i++) {
                while (randomComStr.equals(checkNumArray[i])) {
                    checkNumArray[i] = null;
                    break loop;
                }
            }
            loop: for (int i = 0; i < bingoLineNumber; i++) {
                for (int j = 0; j < bingoLineNumber; j++) {
                    if (randomComStr.equals(bingo[i][j]) && (!bingo[i][j].equals("O"))
                            && (!bingo[i][j].equals("X"))) {
                        bingo[i][j] = "X";
                        break loop;
                    }
                }
            }
            loop: for (int i = 0; i < bingoLineNumber; i++) {
                for (int j = 0; j < bingoLineNumber; j++) {
                    if (randomComStr.equals(otherBingo.bingo[i][j]) && (!otherBingo.bingo[i][j].equals("O"))
                            && (!otherBingo.bingo[i][j].equals("X"))) {
                        otherBingo.bingo[i][j] = "X";
                        break loop;
                    }
                }
            }

        }
        System.out.println("_____");

    }

    public void bingoIsFull(Bingo bingo){
        loop:
        for(int i=0; i<bingoLineNumber; i++){
            for(int j=0; j<bingoLineNumber; j++){
                if(!bingo.bingo[i][j].equals("O") || !bingo.bingo[i][j].equals("X")){
                    break loop;
                } 
                if(bingo.bingo[bingoLineNumber-1][bingoLineNumber-1].equals("O")|| bingo.bingo[bingoLineNumber-1][bingoLineNumber-1].equals("X")){
                    stopCheckBingo=false;
                    break loop;
                }


            } 
        }
    }

    public void detectBingo(String bingoSymbol) {
        int diagonalCountBingo = 0;
        int horizontalCountBingo = 0;
        int verticalCountBingo = 0;

        for (int i = 0; i < bingoLineNumber; i++) {
            if (bingo[i][i].equals(bingoSymbol)) {
                diagonalCountBingo += 1;
            }
        }
        if (diagonalCountBingo == bingoLineNumber) {
            bingoCount += 1;
        }
        for (int i = 0; i < bingoLineNumber; i++) {

            for (int k = 0; k < bingoLineNumber; k++) {
                if (bingo[i][k].equals(bingoSymbol)) {
                    horizontalCountBingo += 1;

                    if (k == bingoLineNumber - 1 && (horizontalCountBingo % bingoLineNumber) == 0) {
                        bingoCount += 1;
                    } else if (k == bingoLineNumber - 1) {
                        horizontalCountBingo = 0;
                    }
                }
            }

        }

        for (int k = 0; k < bingoLineNumber; k++) {
            for (int i = 0; i < bingoLineNumber; i++) {
                if (bingo[i][k].equals(bingoSymbol)) {
                    verticalCountBingo += 1;
                    if (i == bingoLineNumber - 1 && (verticalCountBingo % bingoLineNumber) == 0) {
                        bingoCount += 1;
                    } else if (k == bingoLineNumber - 1) {
                        verticalCountBingo = 0;
                    }
                }
            }
        }

    }

    public int returnBingoCount() {

        return bingoCount;
    }
}
