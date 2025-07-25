package com.darffin.config;

import com.darffin.model.Card;
import com.darffin.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Component
public class DatabaseInitializer {

    @Autowired
    private CardService cardService;

    @PostConstruct
    public void init() {
        List<Card> existingCards = cardService.getAllCards();
        if (existingCards.isEmpty()) {
            Card fireball = new Card();

            fireball.setName("Fire Ball");
            fireball.setEffect("Inflicts 6 damage");

            cardService.saveCard(fireball);
        }
    }

}
