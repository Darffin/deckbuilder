package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerController {
    Player player = Player.getInstance();

    @Autowired
    private CardController cardController;

    public PlayerController(){
        //defaultDeck();
    }

    public void decreaseLife(int value){
        player.setLife(player.getLife() - value);
    }

    public List<Card> defaultDeck(){
        List<Card> deck = new ArrayList<Card>();
        deck = cardController.listAllCards();

        /*
        deck.add(cardController.getCardByName("Fire ball"));
        deck.add(cardController.getCardByName("Cumulonimbus"));
        deck.add(cardController.getCardByName("Fire Dart"));
        deck.add(cardController.getCardByName("Water Splash"));
        deck.add(cardController.getCardByName("Quagmire"));
        deck.add(cardController.getCardByName("Lich Wind"));
        deck.add(cardController.getCardByName("Inferno Shuriken"));
        deck.add(cardController.getCardByName("Fire ball"));


         */

        player.setDeck(deck);

        return deck;
    }

}
