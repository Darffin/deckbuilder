package com.darffin.service;

import com.darffin.model.Card;
import com.darffin.repository.CardRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    private List<Card> discardDeck = new ArrayList<Card>();

    List<Card> commons = new ArrayList<>();
    List<Card> rares = new ArrayList<>();
    List<Card> epics = new ArrayList<>();
    List<Card> legendaries = new ArrayList<>();

    public CardService(CardRepository cardRepository){
        for (Card card : cardRepository.findAll()) {
            switch (card.getRarity()) {
                case "Common":
                    commons.add(card);
                    break;
                case "Rare":
                    rares.add(card);
                    break;
                case "Epic":
                    epics.add(card);
                    break;
                case "Legendary":
                    legendaries.add(card);
                    break;
            }
        }
    }


    public Card getCardForChest(){
        List<Card> possibleCards = new ArrayList<>();

        possibleCards.addAll(getRandomCards(commons, 4));
        possibleCards.addAll(getRandomCards(rares, 3));
        possibleCards.addAll(getRandomCards(epics, 2));
        possibleCards.addAll(getRandomCards(legendaries, 1));

        int randomIndex = ThreadLocalRandom.current().nextInt(10);
        return possibleCards.get(randomIndex);
    }

    public List<Card> getRandomCards(List<Card> source, int count) {
        Collections.shuffle(source);
        return source.subList(0, Math.min(count, source.size()));
    }

    public void saveCard(Card card) {
        cardRepository.save(card);
    }


    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardByName(String name) {
        return cardRepository.findByName(name);
    }

    public List<Card> allCardsDeck(){ return getAllCards(); }

    public List<Card> defaultDeck(){
        List<Card> deck = new ArrayList<Card>();
        //deck = this.getAllCards();

        deck.add(cardRepository.findByName("Fire Ball"));
        deck.add(cardRepository.findByName("Cumulonimbus"));
        deck.add(cardRepository.findByName("Fire Dart"));
        deck.add(cardRepository.findByName("Water Splash"));
        deck.add(cardRepository.findByName("Quagmire"));
        deck.add(cardRepository.findByName("Lich Wind"));
        deck.add(cardRepository.findByName("Inferno Shuriken"));
        deck.add(cardRepository.findByName("Fire Ball"));

        return deck;
    }

    public List<Card> commonDeck(){
        List<Card> deck = new ArrayList<Card>();
        //deck = this.getAllCards();

        for(Card a : getAllCards()){
            if(a.getRarity().equals("Common")){
                deck.add(a);
            }
        }

        /*
        deck.add(cardRepository.findByName("Fire Ball"));
        deck.add(cardRepository.findByName("Cumulonimbus"));
        deck.add(cardRepository.findByName("Fire Dart"));
        deck.add(cardRepository.findByName("Water Splash"));
        deck.add(cardRepository.findByName("Quagmire"));
        deck.add(cardRepository.findByName("Lich Wind"));
        deck.add(cardRepository.findByName("Inferno Shuriken"));
        deck.add(cardRepository.findByName("Fire Ball"));

         */
        return deck;
    }

    public List<Card> rareDeck(){
        List<Card> deck = new ArrayList<Card>();
        //deck = this.getAllCards();

        for(Card a : getAllCards()){
            if(a.getRarity().equals("Rare")){
                deck.add(a);
            }
        }
        deck.add(cardRepository.findByName("Mana Beacon"));

        /*
        deck.add(cardRepository.findByName("Fire Ball"));
        deck.add(cardRepository.findByName("Cumulonimbus"));
        deck.add(cardRepository.findByName("Fire Dart"));
        deck.add(cardRepository.findByName("Water Splash"));
        deck.add(cardRepository.findByName("Quagmire"));
        deck.add(cardRepository.findByName("Lich Wind"));
        deck.add(cardRepository.findByName("Inferno Shuriken"));
        deck.add(cardRepository.findByName("Fire Ball"));

         */
        return deck;
    }

    public List<Card> epicDeck(){
        List<Card> deck = new ArrayList<Card>();
        //deck = this.getAllCards();

        for(Card a : getAllCards()){
            if(a.getRarity().equals("Epic")){
                deck.add(a);
            }
        }
        deck.add(cardRepository.findByName("Mana Beacon"));
        /*
        deck.add(cardRepository.findByName("Fire Ball"));
        deck.add(cardRepository.findByName("Cumulonimbus"));
        deck.add(cardRepository.findByName("Fire Dart"));
        deck.add(cardRepository.findByName("Water Splash"));
        deck.add(cardRepository.findByName("Quagmire"));
        deck.add(cardRepository.findByName("Lich Wind"));
        deck.add(cardRepository.findByName("Inferno Shuriken"));
        deck.add(cardRepository.findByName("Fire Ball"));

         */
        return deck;
    }

    public List<Card> legendaryDeck(){
        List<Card> deck = new ArrayList<Card>();
        //deck = this.getAllCards();

        for(Card a : getAllCards()){
            if(a.getRarity().equals("Legendary")){
                deck.add(a);
            }
        }
        deck.add(cardRepository.findByName("Mana Beacon"));
        /*
        deck.add(cardRepository.findByName("Fire Ball"));
        deck.add(cardRepository.findByName("Cumulonimbus"));
        deck.add(cardRepository.findByName("Fire Dart"));
        deck.add(cardRepository.findByName("Water Splash"));
        deck.add(cardRepository.findByName("Quagmire"));
        deck.add(cardRepository.findByName("Lich Wind"));
        deck.add(cardRepository.findByName("Inferno Shuriken"));
        deck.add(cardRepository.findByName("Fire Ball"));

         */
        return deck;
    }

    public List<Card> lunaDefaultDeck(){
        List<Card> deck = new ArrayList<Card>();
        //deck = this.getAllCards();

        deck.add(cardRepository.findByName("Quagmire"));
        deck.add(cardRepository.findByName("Quagmire"));
        deck.add(cardRepository.findByName("Water Splash"));
        deck.add(cardRepository.findByName("Water Splash"));
        deck.add(cardRepository.findByName("Inoffensive Frog"));
        deck.add(cardRepository.findByName("Poison Arrow"));

        return deck;
    }

    public List<Card> solanoDefaultDeck(){
        List<Card> deck = new ArrayList<Card>();
        //deck = this.getAllCards();

        deck.add(cardRepository.findByName("Strategic Attack"));
        deck.add(cardRepository.findByName("Fire Ball"));
        deck.add(cardRepository.findByName("Fire Ball"));
        deck.add(cardRepository.findByName("Fire Dart"));
        deck.add(cardRepository.findByName("Poison Arrow"));
        deck.add(cardRepository.findByName("Shield Throw"));

        return deck;
    }

    public void addToDiscardDeck(Card e){
        discardDeck.add(e);
    }

    public List<Card> getDiscardDeck() {
        return discardDeck;
    }

    public void setDiscardDeck(List<Card> discardDeck) {
        this.discardDeck = discardDeck;
    }



}
