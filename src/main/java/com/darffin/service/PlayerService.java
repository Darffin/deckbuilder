package com.darffin.service;

import com.darffin.controller.CardController;
import com.darffin.model.Card;
import com.darffin.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PlayerService {

    private final Player player = Player.getInstance();

    @Autowired
    private CardService cardService;

    public void Luna(){
        player.setLife(60);
        player.setMana(6);
        player.setGold(40);
        player.setDeckPlayer(cardService.lunaDefaultDeck());
    }

    public void Disciple(){
        player.setLife(50);
        player.setMana(6); // Just for test
        player.setGold(200); // ==
        player.setDeckPlayer(cardService.allCardsDeck()); // ==
    }

    public void Solano(){
        player.setLife(80);
        player.setMana(3);
        player.setGold(80);
        player.setDeckPlayer(cardService.solanoDefaultDeck());
    }

    public int playerLife(){
        return player.getLife();
    }

    public void damagePlayer(int damage) {
        if(damage > player.getShield()){
            player.setLife(player.getLife() - (damage - player.getShield()));
            player.setShield(0);
        }else {
            player.setShield(player.getShield() - damage);
        }

    }

    public List<Card> playerDeck(){
        return player.getDeckPlayer();
    }

    public void setPlayerDeck(List<Card> e){
        player.setDeckPlayer(e);
    }

    public void setPlayerDeckDefault(){
        player.setDeckPlayer(cardService.defaultDeck());
    }

    public void uploadPlayerEffects(){
        if(player.getStrength() > 0){
            player.setStrength(0); // Player lose 1 of strength each round is too OP, instead, reset each round
        }

        player.setShield(0); // At the end of enemy turn, player lose all shield, abilities update will have a power to stop that.
    }

    public void verifyDeckAvailability(){
        if (playerDeck().isEmpty()){
            playerDeck().addAll(cardService.getDiscardDeck());
            Collections.shuffle(playerDeck());
            cardService.getDiscardDeck().clear();
        }
    }

    public void resetEffects(){
        player.setShield(0);
        player.setStrength(0);
    }

    public int playerMana(){
        return player.getMana();
    }

    public int playerGold(){
        return player.getGold();
    }

    public void spendGold(int gold){
        player.setGold(player.getGold() - gold);
    }

    public void earnGold(int gold){
        player.setGold(player.getGold() + gold);
    }

    public void setPlayerMana(int e){
        this.player.setMana(e);
    }

    public int shield(){
        return player.getShield();
    }

    public int strength(){
        return player.getStrength();
    }

    public void giveShield(int value){
        player.setShield(shield()+value);
    }

    public void giveStrength(int value){
        player.setStrength(strength()+value);
    }

    public Player getPlayer() {
        return player;
    }
}
