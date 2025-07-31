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

            // ------** Common Cards **------
            Card fireball = new Card();

            fireball.setName("Fire Ball");
            fireball.setEffect("Inflicts 6 damage and 2 burn");
            fireball.setCost(20);
            fireball.setRarity("Common");
            cardService.saveCard(fireball);

            // ----------------
            Card ancestralStrength = new Card();

            ancestralStrength.setName("Ancestral Strength");
            ancestralStrength.setEffect("Gain 4 strength");
            ancestralStrength.setCost(20);
            ancestralStrength.setRarity("Common");
            cardService.saveCard(ancestralStrength);

            // ----------------
            Card fireDart = new Card();

            fireDart.setName("Fire Dart");
            fireDart.setEffect("Inflicts 2 damage and 1 burn");
            fireDart.setCost(15);
            fireDart.setRarity("Common");
            cardService.saveCard(fireDart);

            // ----------------
            Card waterSplash = new Card();

            waterSplash.setName("Water Splash");
            waterSplash.setEffect("Inflicts 5 damage");
            waterSplash.setCost(20);
            waterSplash.setRarity("Common");
            cardService.saveCard(waterSplash);

            // ----------------
            Card quagmire = new Card();

            quagmire.setName("Quagmire");
            quagmire.setEffect("Inflicts 5 damage and 2 poison");
            quagmire.setCost(30);
            quagmire.setRarity("Common");
            cardService.saveCard(quagmire);

            // ----------------
            Card poisonArrow = new Card();

            poisonArrow.setName("Poison Arrow");
            poisonArrow.setEffect("Inflicts 2 poison and active poison");
            poisonArrow.setCost(30);
            poisonArrow.setRarity("Common");
            cardService.saveCard(poisonArrow);

            // ----------------
            Card highShield = new Card();

            highShield.setName("High Shield");
            highShield.setEffect("Gain 8 Shield");
            highShield.setCost(30);
            highShield.setRarity("Common");
            cardService.saveCard(highShield);

            // ----------------
            Card shieldThrow = new Card();

            shieldThrow.setName("Shield Throw");
            shieldThrow.setEffect("Inflicts damage equal your shield, lose all your shield");
            shieldThrow.setCost(30);
            shieldThrow.setRarity("Common");
            cardService.saveCard(shieldThrow);

            // ------** Rare Cards **------
            Card inoffensiveFrog = new Card();

            inoffensiveFrog.setName("Inoffensive Frog");
            inoffensiveFrog.setEffect("Inflicts 8 poison and active poison");
            inoffensiveFrog.setCost(30);
            inoffensiveFrog.setRarity("Rare");
            cardService.saveCard(inoffensiveFrog);

            // ----------------
            Card fuelToFlames = new Card();

            fuelToFlames.setName("Fuel To Flames");
            fuelToFlames.setEffect("Inflicts 8 burn");
            fuelToFlames.setCost(30);
            fuelToFlames.setRarity("Rare");
            cardService.saveCard(fuelToFlames);

            // ----------------
            Card infernoShuriken = new Card();

            infernoShuriken.setName("Inferno Shuriken");
            infernoShuriken.setEffect("Inflicts 6 damage and 3 of burn");
            infernoShuriken.setCost(30);
            infernoShuriken.setRarity("Rare");
            cardService.saveCard(infernoShuriken);

            // ----------------
            Card legalAnabolic = new Card();

            legalAnabolic.setName("Legal Anabolic");
            legalAnabolic.setEffect("Gain 8 Strength");
            legalAnabolic.setCost(30);
            legalAnabolic.setRarity("Rare");
            cardService.saveCard(legalAnabolic);

            // ------** Epic Cards **------
            Card manaBeacon = new Card();

            manaBeacon.setName("Mana Beacon");
            manaBeacon.setEffect("Gain 2 mana this turn");
            manaBeacon.setCost(55);
            manaBeacon.setRarity("Epic");
            cardService.saveCard(manaBeacon);

            // ----------------
            Card strategicAttack = new Card();

            strategicAttack.setName("Strategic Attack");
            strategicAttack.setEffect("Inflicts 5 damage, gain 5 Shield");
            strategicAttack.setCost(50);
            strategicAttack.setRarity("Epic");
            cardService.saveCard(strategicAttack);

            // ----------------
            Card lichWind = new Card();

            lichWind.setName("Lich Wind");
            lichWind.setEffect("Inflicts 5 damage and 13 poison");
            lichWind.setCost(50);
            lichWind.setRarity("Epic");
            cardService.saveCard(lichWind);

            // ----------------
            Card shieldBash = new Card();

            shieldBash.setName("Shield Bash");
            shieldBash.setEffect("Inflicts damage equal your shield");
            shieldBash.setCost(55);
            shieldBash.setRarity("Epic");
            cardService.saveCard(shieldBash);

            // ------** Legendary Cards **------
            Card cumulonimbus = new Card();

            cumulonimbus.setName("Cumulonimbus");
            cumulonimbus.setEffect("Inflicts 10 damage and gain 5 strength");
            cumulonimbus.setCost(75);
            cumulonimbus.setRarity("Legendary");
            cardService.saveCard(cumulonimbus);

            // ----------------
            Card hazardWorld = new Card();

            hazardWorld.setName("Hazard World");
            hazardWorld.setEffect("Double and active poison");
            hazardWorld.setCost(85);
            hazardWorld.setRarity("Legendary");
            cardService.saveCard(hazardWorld);

            // ----------------
            Card adrenalineSpike = new Card();

            adrenalineSpike.setName("Adrenaline Spike");
            adrenalineSpike.setEffect("Take 8 damage and double your strength");
            adrenalineSpike.setCost(80);
            adrenalineSpike.setRarity("Legendary");
            cardService.saveCard(adrenalineSpike);

            // ----------------
            Card stoneDome = new Card();

            stoneDome.setName("Stone Dome");
            stoneDome.setEffect("Gain 20 shield");
            stoneDome.setCost(65);
            stoneDome.setRarity("Legendary");
            cardService.saveCard(stoneDome);
        }

    }

}
