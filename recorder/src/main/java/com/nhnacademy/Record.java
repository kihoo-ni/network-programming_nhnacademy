package com.nhnacademy;

public class Record {
    int battleNum;
    int victoryNum;

    public Record(int battleNum, int victoryNum){
        this.battleNum=battleNum;
        this.victoryNum=victoryNum;
    }

    public int getBattleNum() {
        return this.battleNum;
    }

    public void setBattleNum(int battleNum) {
        this.battleNum = battleNum;
    }

    public int getVictoryNum() {
        return this.victoryNum;
    }

    public void setVictoryNum(int victoryNum) {
        this.victoryNum = victoryNum;
    }

}
