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
            fireball.setCost(20);
            fireball.setRarity("Common");

            Card cumulonimbus = new Card();

            cumulonimbus.setName("Cumulonimbus");
            cumulonimbus.setEffect("Inflicts 10 damage");
            cumulonimbus.setCost(75);
            cumulonimbus.setRarity("Legendary");

            Card fireDart = new Card();

            fireDart.setName("Fire Dart");
            fireDart.setEffect("Inflicts 2 damage");
            fireDart.setCost(15);
            fireDart.setRarity("Common");

            Card waterSplash = new Card();

            waterSplash.setName("Water Splash");
            waterSplash.setEffect("Inflicts 5 damage");
            waterSplash.setCost(20);
            waterSplash.setRarity("Common");

            Card quagmire = new Card();

            quagmire.setName("Quagmire");
            quagmire.setEffect("Inflicts 8 damage");
            quagmire.setCost(30);
            quagmire.setRarity("Rare");

            Card lichWind = new Card();

            lichWind.setName("Lich Wind");
            lichWind.setEffect("Inflicts 13 damage");
            lichWind.setCost(50);
            lichWind.setRarity("Epic");

            Card infernoShuriken = new Card();

            infernoShuriken.setName("Inferno Shuriken");
            infernoShuriken.setEffect("Inflicts 11 damage");
            infernoShuriken.setCost(50);
            infernoShuriken.setRarity("Epic");

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
