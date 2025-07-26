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

            Card cumulonimbus = new Card();

            cumulonimbus.setName("Cumulonimbus");
            cumulonimbus.setEffect("Inflicts 10 damage");

            Card fireDart = new Card();

            fireDart.setName("Fire Dart");
            fireDart.setEffect("Inflicts 2 damage");

            Card waterSplash = new Card();

            waterSplash.setName("Water Splash");
            waterSplash.setEffect("Inflicts 5 damage");

            Card quagmire = new Card();

            quagmire.setName("Quagmire");
            quagmire.setEffect("Inflicts 8 damage");

            Card lichWind = new Card();

            lichWind.setName("Lich Wind");
            lichWind.setEffect("Inflicts 13 damage");

            Card infernoShuriken = new Card();

            infernoShuriken.setName("Inferno Shuriken");
            infernoShuriken.setEffect("Inflicts 11 damage");

            cardService.saveCard(fireball);
            cardService.saveCard(cumulonimbus);
            cardService.saveCard(fireDart);
            cardService.saveCard(waterSplash);
            cardService.saveCard(quagmire);
            cardService.saveCard(lichWind);
            cardService.saveCard(infernoShuriken);
        }
    }

}
