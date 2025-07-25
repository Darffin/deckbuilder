package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;


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
}
