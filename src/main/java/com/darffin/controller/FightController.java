package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Component
public class FightController {
    @FXML
    private Button card1;
    @FXML
    private Button card2;
    @FXML
    private Button card3;
    @FXML
    private Button card4;
    @FXML
    private Label playerLife;
    @FXML
    private Label enemyLife;
    @FXML
    private Label enemyName;
    @FXML
    private Label deck;
    @FXML
    private Label discardDeck;
    @FXML
    private Button endTurn;

    private Card card;

    @Autowired
    private PlayerController playerController;
    Player player = Player.getInstance();

    private List<Card> deckPlayer = new ArrayList<Card>();

    public FightController(){
    }

    public void prepareNewGame(){
        playerLife.setText(player.getLife()+"");
        playerController.defaultDeck();
        this.deckPlayer = player.getDeck();

        Collections.shuffle(deckPlayer);

        card1.setText(deckPlayer.get(0).getName());
        deckPlayer.remove(0);
        card2.setText(deckPlayer.get(1).getName());
        deckPlayer.remove(1);
        card3.setText(deckPlayer.get(2).getName());
        deckPlayer.remove(2);
        card4.setText(deckPlayer.get(3).getName());
        deckPlayer.remove(3);

        deck.setText(deckPlayer.size()+"");

    }

    public void cardAction(){

    }

    public void playerTurn(){
        card1.setText(deckPlayer.get(0).getEffect());
        card2.setText(deckPlayer.get(1).getEffect());
        card3.setText(deckPlayer.get(2).getEffect());
        card4.setText(deckPlayer.get(3).getEffect());

    }

    public void enemyTurn(){

    }





}
