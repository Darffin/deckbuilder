package com.darffin.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int life;
    private int mana;
    private List<Card> deckPlayer = new ArrayList<Card>();
    private static Player player;

    private Player(){
        this.life = 60;
        this.mana = 3;
    }

    public static Player getInstance(){
        if(player == null) player = new Player();
        return player;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public List<Card> getDeckPlayer() {
        return deckPlayer;
    }

    public void setDeckPlayer(List<Card> deckPlayer) {
        this.deckPlayer = deckPlayer;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }


}
