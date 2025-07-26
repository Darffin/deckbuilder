package com.darffin.service;

import com.darffin.model.Card;
import com.darffin.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    private List<Card> discardDeck = new ArrayList<Card>();

    @Autowired
    private CardRepository cardRepository;

    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardByName(String name) {
        return cardRepository.findByName(name);
    }

    public List<Card> defaultDeck(){
        List<Card> deck = new ArrayList<Card>();
        deck = this.getAllCards();

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
