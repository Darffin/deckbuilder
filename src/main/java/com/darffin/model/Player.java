package com.darffin.model;

import java.util.List;

public class Player {
    private int life;
    private List<Card> deck;
    private static Player player;

    private Player(){
        this.life = 60;
    }

    public static Player getInstance(){
        if(player == null) player = new Player();
        return player;
    }

    public void decreaseLife(int value){
        this.life -= value;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }


}
