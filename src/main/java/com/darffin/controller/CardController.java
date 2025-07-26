package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.service.CardService;
import com.darffin.service.PlayerService;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Card> listAllCards() {
        return cardService.getAllCards();
    }

    @PostMapping
    public void createCard(@RequestBody Card card) {
        cardService.saveCard(card);
    }

    public Card getCardByName(String name) {
        return cardService.getCardByName(name);
    }

    public void cardButtonsUpdate(Button card1, Button card2, Button card3, Button card4){
        Collections.shuffle(playerService.playerDeck());

        card1.setText(playerService.playerDeck().get(0).getName());
        //cardService.addToDiscardDeck(playerService.playerDeck().get(0));
        playerService.playerDeck().remove(0);


        card2.setText(playerService.playerDeck().get(1).getName());
        //cardService.addToDiscardDeck(playerService.playerDeck().get(1));
        playerService.playerDeck().remove(1);


        card3.setText(playerService.playerDeck().get(2).getName());
        //cardService.addToDiscardDeck(playerService.playerDeck().get(2));
        playerService.playerDeck().remove(2);


        card4.setText(playerService.playerDeck().get(3).getName());
        //cardService.addToDiscardDeck(playerService.playerDeck().get(3));
        playerService.playerDeck().remove(3);


    }





}
