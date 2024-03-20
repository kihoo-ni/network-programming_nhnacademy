package com.nhnacademy;

public class Item {
    String id;
    String model;
    int hp;
    int attack;
    int protection;
    int moveSpeed;
    int attackSpeed;

    public Item(String id, String model, int hp, int attack, int protection, int moveSpeed, int attackSpeed){
        this.id=id;
        this.model=model;
        this.hp=hp;
        this.protection=protection;
        this.moveSpeed=moveSpeed;
        this.attackSpeed=attackSpeed;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getProtection() {
        return this.protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getMoveSpeed() {
        return this.moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

}
