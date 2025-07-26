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

    public int playerLife(){
        return player.getLife();
    }

    public void damagePlayer(int damage) {
        player.setLife(player.getLife() - damage);
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

    public void verifyDeckAvailability(){
        if (playerDeck().isEmpty()){
            playerDeck().addAll(cardService.getDiscardDeck());
            Collections.shuffle(playerDeck());
            cardService.getDiscardDeck().clear();
        }
    }

    public int playerMana(){
        return player.getMana();
    }

    public void setPlayerMana(int e){
        this.player.setMana(e);
    }

}
