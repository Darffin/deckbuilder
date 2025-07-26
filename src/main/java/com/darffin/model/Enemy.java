package com.darffin.model;

public class Enemy {
    private int life;
    private String name;
    private static Enemy enemy;

    public Enemy(){
        this.life = 200;
        this.name = "Forest Lizard";
    }

    public static Enemy getEnemyInstance(){
        if (enemy == null) enemy = new Enemy();
        return enemy;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

}
