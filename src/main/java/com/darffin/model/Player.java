package com.darffin.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    private Long id = 1L;

    private int life;
    private int mana;
    private int gold;
    private int shield;
    private int strength;

    private String lastNodeId;

    @ManyToMany
    @JoinTable(
            name = "player_deck",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> deckPlayer = new ArrayList<Card>();

    private static Player player;

    //private Player(){
    //    this.life = 60;
    //    this.mana = 6;
    //}

    public static Player getInstance(){
        if(player == null) player = new Player();
        return player;
    }

    public static void resetInstance(){
        player = new Player();
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

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getShield() {
        return shield;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Player.player = player;
    }

    public String getLastNodeId() {
        return lastNodeId;
    }

    public void setLastNodeId(String lastNodeId) {
        this.lastNodeId = lastNodeId;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
