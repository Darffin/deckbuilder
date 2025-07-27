package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.model.Player;
import com.darffin.service.*;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.*;


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
    private Label playerMana;
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

    @Autowired
    private CardController cardController;

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    CardService cardService;

    @Autowired
    CardEffectService cardEffectService;

    @Autowired
    EnemyService enemyService;



    public FightController(){
    }

    public void prepareNewGame(){
        playerLife.setText(playerService.playerLife()+"");
        enemyName.setText(enemyService.enemyName());
        enemyLife.setText(enemyService.enemyLife()+"");
        gameService.setMana(playerService.playerMana());

        playerMana.setText(gameService.getMana() +" / "+ playerService.playerMana());

        playerService.setPlayerDeckDefault();

        cardController.cardButtonsUpdate(card1,card2,card3,card4);

        updateLabel();

    }

    public void cardAction(ActionEvent event){
        Button clickedButton = (Button) event.getSource();
        Card selectedCard = cardController.getCardByName(clickedButton.getText());
        String methodName = selectedCard.getName().trim().replaceAll("\\s+", "");

        if(gameService.getMana() > 0) {
            try {
                Method method = CardEffectService.class.getMethod(methodName);
                method.invoke(cardEffectService);
                enemyLife.setText(enemyService.enemyLife() + "");

                cardService.addToDiscardDeck(selectedCard);

                gameService.setMana(gameService.getMana() - 1);
                //playerService.verifyDeckAvailability();

                clickedButton.setText(playerService.playerDeck().get(0).getName());
                playerService.playerDeck().remove(0);

                playerService.verifyDeckAvailability();

                updateLabel();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else notEnoughMana();

        //Preciso pegar uma carta do deck e atualizar o botão
    }

    public void updateLabel(){
        deck.setText(playerService.playerDeck().size()+"");
        discardDeck.setText(cardService.getDiscardDeck().size()+"");
        playerMana.setText(gameService.getMana() +" / "+ playerService.playerMana());
        playerLife.setText(playerService.playerLife() + "");
    }

    public void notEnoughMana(){
        Timeline blinkMana = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> playerMana.setTextFill(Color.RED)),
                new KeyFrame(Duration.seconds(0.25), e -> playerMana.setTextFill(Color.BLACK)),
                new KeyFrame(Duration.seconds(0.5),  e -> playerMana.setTextFill(Color.RED)),
                new KeyFrame(Duration.seconds(0.75), e -> playerMana.setTextFill(Color.BLACK)),
                new KeyFrame(Duration.seconds(1),  e -> playerMana.setTextFill(Color.RED)),
                new KeyFrame(Duration.seconds(1.25), e -> playerMana.setTextFill(Color.BLACK))
        );
        blinkMana.play();
    }

    public void playerTurn(){
        card1.setText(playerService.playerDeck().get(0).getEffect());
        card2.setText(playerService.playerDeck().get(1).getEffect());
        card3.setText(playerService.playerDeck().get(2).getEffect());
        card4.setText(playerService.playerDeck().get(3).getEffect());

    }

    public void enemyTurn(){
        Timeline enemyturn = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> endTurn.setDisable(true)),

                new KeyFrame(Duration.seconds(0.25), e -> card1.setDisable(true)),
                new KeyFrame(Duration.seconds(0.25), e -> { cardController.cardButtonUnload(card1); updateLabel();}),
                new KeyFrame(Duration.seconds(0.5), e -> card2.setDisable(true)),
                new KeyFrame(Duration.seconds(0.5), e -> { cardController.cardButtonUnload(card2); updateLabel();}),
                new KeyFrame(Duration.seconds(0.75), e -> card3.setDisable(true)),
                new KeyFrame(Duration.seconds(0.75), e -> { cardController.cardButtonUnload(card3); updateLabel();}),
                new KeyFrame(Duration.seconds(1), e -> card4.setDisable(true)),
                new KeyFrame(Duration.seconds(1), e -> { cardController.cardButtonUnload(card4); updateLabel();}),

                new KeyFrame(Duration.seconds(1.25), e -> playerService.damagePlayer(10)),

                new KeyFrame(Duration.seconds(1.5), e -> card1.setDisable(false)),
                new KeyFrame(Duration.seconds(1.5), e -> { playerService.verifyDeckAvailability(); cardController.cardButtonLoad(card1); updateLabel();}),
                new KeyFrame(Duration.seconds(1.75), e -> card2.setDisable(false)),
                new KeyFrame(Duration.seconds(1.75), e -> { playerService.verifyDeckAvailability(); cardController.cardButtonLoad(card2); updateLabel();}),
                new KeyFrame(Duration.seconds(2), e -> card3.setDisable(false)),
                new KeyFrame(Duration.seconds(2), e -> { playerService.verifyDeckAvailability(); cardController.cardButtonLoad(card3); updateLabel();}),
                new KeyFrame(Duration.seconds(2.25), e -> card4.setDisable(false)),
                new KeyFrame(Duration.seconds(2.25), e -> { playerService.verifyDeckAvailability(); cardController.cardButtonLoad(card4); updateLabel();}),

                new KeyFrame(Duration.seconds(2.5),  e -> updateMana()),
                new KeyFrame(Duration.seconds(2.5),  e -> endTurn.setDisable(false))

        );
        enemyturn.play();
    }

    public void updateMana(){
        gameService.reloadMana();
        playerMana.setText(gameService.getMana()+" / "+playerService.playerMana());
    }

    public void endTurn(){
        enemyTurn();
    }





}
