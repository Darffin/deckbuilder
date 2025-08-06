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
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;


@Component
public class FightController {
    @Autowired
    private ApplicationContext context;

    @FXML
    private Button card1, card2, card3, card4;
    @FXML
    private Label card1Effect, card2Effect, card3Effect, card4Effect;
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
    @FXML
    private Label enemyIntention;
    @FXML
    private Label intentionValue;
    @FXML
    private Label effect1;
    @FXML
    private Label effect2;
    @FXML
    private Label enemyDebuff1;
    @FXML
    private Label enemyDebuff2;
    @FXML
    private Button result, menu;


    @Autowired
    private PlayerController playerController;

    @Autowired
    private CardController cardController;

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerProgressService playerProgressService;

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
        enemyIntention.setText(enemyService.enemyIntention().get(0));
        intentionValue.setText(enemyService.intentionValue().get(0)+"");

        gameService.setMana(playerService.playerMana());

        playerMana.setText(gameService.getMana() +" / "+ playerService.playerMana());

        //playerService.setPlayerDeckDefault();

        cardController.cardButtonsUpdate(card1,card2,card3,card4);
        updateCardLabels();
        resizeFontCard(card1); resizeFontCard(card2); resizeFontCard(card3); resizeFontCard(card4);
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

                verifyMatchResult();

                cardService.addToDiscardDeck(selectedCard);

                gameService.setMana(gameService.getMana() - 1);
                playerService.verifyDeckAvailability();

                clickedButton.setText(playerService.playerDeck().get(0).getName());
                updateCardLabels();
                resizeFontCard(clickedButton);
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
        enemyLife.setText(enemyService.enemyLife() + "");

        verifyEffects();
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

    public void enemyTurn(){ // Brazilian gambiarra
        Timeline enemyturn = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> endTurn.setDisable(true)),

                new KeyFrame(Duration.seconds(0.25), e -> card1.setDisable(true)),
                new KeyFrame(Duration.seconds(0.25), e -> { cardController.cardButtonUnload(card1); card1Effect.setText(""); updateLabel();}),
                new KeyFrame(Duration.seconds(0.5), e -> card2.setDisable(true)),
                new KeyFrame(Duration.seconds(0.5), e -> { cardController.cardButtonUnload(card2); card2Effect.setText(""); updateLabel();}),
                new KeyFrame(Duration.seconds(0.75), e -> card3.setDisable(true)),
                new KeyFrame(Duration.seconds(0.75), e -> { cardController.cardButtonUnload(card3); card3Effect.setText(""); updateLabel();}),
                new KeyFrame(Duration.seconds(1), e -> card4.setDisable(true)),
                new KeyFrame(Duration.seconds(1), e -> { cardController.cardButtonUnload(card4); card4Effect.setText(""); updateLabel();}),
                new KeyFrame(Duration.seconds(1.25), e -> { gameService.applyGameEffect("start"); updateLabel();}), // add poison and strength later

                new KeyFrame(Duration.seconds(1.5), e -> this.enemyMove()),

                new KeyFrame(Duration.seconds(1.75), e -> { verifyMatchResult(); gameService.applyGameEffect("end"); updateLabel();}),
                new KeyFrame(Duration.seconds(1.75), e -> { playerService.verifyDeckAvailability(); cardController.cardButtonLoad(card1); updateCardLabel(card1, card1Effect); resizeFontCard(card1); updateLabel(); card1.setDisable(false);}),
                new KeyFrame(Duration.seconds(2), e -> { playerService.verifyDeckAvailability(); cardController.cardButtonLoad(card2); updateCardLabel(card2, card2Effect); resizeFontCard(card2);updateLabel(); card2.setDisable(false);}),
                new KeyFrame(Duration.seconds(2.25), e -> { playerService.verifyDeckAvailability(); cardController.cardButtonLoad(card3); updateCardLabel(card3, card3Effect); resizeFontCard(card3); updateLabel(); card3.setDisable(false);}),
                new KeyFrame(Duration.seconds(2.5), e -> { playerService.verifyDeckAvailability(); cardController.cardButtonLoad(card4); updateCardLabel(card4, card4Effect); resizeFontCard(card4); updateLabel(); card4.setDisable(false);}),

                new KeyFrame(Duration.seconds(2.75),  e -> updateEnemyState()),
                new KeyFrame(Duration.seconds(2.75),  e -> { playerService.uploadPlayerEffects(); updateLabel(); }),
                new KeyFrame(Duration.seconds(2.75),  e -> updateMana()),
                new KeyFrame(Duration.seconds(2.75),  e -> endTurn.setDisable(false))

        );
        enemyturn.play();
    }

    public void enemyMove(){

        String methodName = enemyService.enemyIntention().get(enemyService.getMoveControl()).trim();
        try {
            Method method = EnemyService.class.getMethod(methodName, int.class);
            method.invoke(enemyService, enemyService.intentionValue().get(enemyService.getMoveControl()));
            updateLabel();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCardLabels(){
        card1Effect.setText(cardService.getCardByName(card1.getText()).getEffect());
        card2Effect.setText(cardService.getCardByName(card2.getText()).getEffect());
        card3Effect.setText(cardService.getCardByName(card3.getText()).getEffect());
        card4Effect.setText(cardService.getCardByName(card4.getText()).getEffect());
    }

    public void updateCardLabel(Button card, Label cardEffect){
        cardEffect.setText(cardService.getCardByName(card.getText()).getEffect());
    }

    public void verifyEffects(){
        System.out.println("Shield: "+playerService.shield() +" Strength:"+ playerService.strength());
        if((effect1.getText().matches("Shield.*"))&&(playerService.shield()<=0)){
            if(playerService.strength() > 0){
                effect1.setText("Strength: "+playerService.strength());
                effect2.setText("");
            } else effect1.setText("");
        }

        if((effect1.getText().matches("Strength.*"))&&(playerService.strength()<=0)){
            if(playerService.shield() > 0){
                effect1.setText("Shield: "+playerService.shield());
            } else effect1.setText("");
        }

        if(((effect2.getText().matches("Shield.*"))&&(playerService.shield()<=0)) || ((effect2.getText().matches("Strength.*"))&&(playerService.strength()<=0))){
            effect2.setText("");
        }

        if(playerService.shield() > 0){
            if(effect1.getText().isEmpty() || effect1.getText().matches("Shield.*")){
                effect1.setText("Shield: "+playerService.shield());
            } else effect2.setText("Shield: "+playerService.shield());
        }

        if(playerService.strength() > 0){
            if(effect1.getText().isEmpty() || effect1.getText().matches("Strength.*")){
                effect1.setText("Strength: " + playerService.strength());
            } else effect2.setText("Strength: " + playerService.strength());
        }

        // --

        System.out.println("Enemy poison: "+gameService.getPoison() +" Enemy burn:"+ gameService.getBurn());
        if((enemyDebuff1.getText().matches("Poison.*"))&&(gameService.getPoison()<=0)){
            if(gameService.getBurn() > 0){
                enemyDebuff1.setText("Burn: " + gameService.getBurn());
                enemyDebuff2.setText("");
            } else enemyDebuff1.setText("");
        }

        if((enemyDebuff1.getText().matches("Burn.*"))&&(gameService.getBurn()<=0)){
            if(gameService.getPoison() > 0){
                enemyDebuff1.setText("Poison: "+gameService.getPoison());
                enemyDebuff2.setText("");
            } else enemyDebuff1.setText("");
        }

        if(((enemyDebuff2.getText().matches("Poison.*"))&&(gameService.getPoison()<=0)) || ((enemyDebuff2.getText().matches("Burn.*"))&&(gameService.getBurn()<=0))){
            enemyDebuff2.setText("");
        }

        if(gameService.getPoison() > 0){
            if(enemyDebuff1.getText().isEmpty() || enemyDebuff1.getText().matches("Poison.*")){
                enemyDebuff1.setText("Poison: "+gameService.getPoison());
            } else enemyDebuff2.setText("Poison: "+gameService.getPoison());
        }

        if(gameService.getBurn() > 0){
            if(enemyDebuff1.getText().isEmpty() || enemyDebuff1.getText().matches("Burn.*")){
                enemyDebuff1.setText("Burn: " + gameService.getBurn());
            } else enemyDebuff2.setText("Burn: " + gameService.getBurn());
        }


    }

    public void updateEnemyState(){
        enemyService.setMoveControl(enemyService.getMoveControl() + 1);
        if(enemyService.getMoveControl() == 6){ enemyService.setMoveControl(0); }
        enemyIntention.setText(enemyService.enemyIntention().get(enemyService.getMoveControl()));
        intentionValue.setText(enemyService.intentionValue().get(enemyService.getMoveControl())+"");
    }

    public void updateMana(){
        gameService.reloadMana();
        playerMana.setText(gameService.getMana()+" / "+playerService.playerMana());
    }

    public void endTurn(){
        enemyTurn();
    }

    public void verifyMatchResult(){
        if(playerService.playerLife()<=0){
            result.setOnAction(event -> {
                try { this.defeat();}
                catch (IOException e) { throw new RuntimeException(e); }
            });
            result.setVisible(true);
            result.setText("Defeat!");
        }
        if(enemyService.enemyLife() <= 0){
            result.setOnAction(event -> {
                try { this.victory();}
                catch (IOException e) { throw new RuntimeException(e); }
            });
            result.setVisible(true);
            result.setText("Victory!");
        }

    }

    public void victory() throws IOException {
        playerService.playerDeck().addAll(cardService.getDiscardDeck());
        cardService.getDiscardDeck().clear();       // Brazilian Gambiarra (temporary)
        playerService.playerDeck().add(cardService.getCardByName(card1.getText()));
        playerService.playerDeck().add(cardService.getCardByName(card2.getText()));
        playerService.playerDeck().add(cardService.getCardByName(card3.getText()));
        playerService.playerDeck().add(cardService.getCardByName(card4.getText()));
        for(Card a : playerService.playerDeck()){ System.out.println(a.getName());}
        playerProgressService.saveProgress(playerService.getPlayer());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/Map.fxml"));
        fxmlLoader.setControllerFactory(context::getBean); // Aqui a mágica acontece
        Parent map = fxmlLoader.load();

        Stage stage = (Stage) result.getScene().getWindow();
        Scene scene = new Scene(map);
        stage.setScene(scene);
    }

    public void defeat() throws IOException {

        playerProgressService.resetPlayerProgress();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/MainMenu.fxml"));
        fxmlLoader.setControllerFactory(context::getBean); // Aqui a mágica acontece
        Parent mainMenu = fxmlLoader.load();

        Stage stage = (Stage) result.getScene().getWindow();
        Scene scene = new Scene(mainMenu);
        stage.setScene(scene);
    }

    public void resizeFontCard(Button cardBtn) {
        double maxWidth = 100;
        double fontSize = 11;

        String[] words = cardBtn.getText().split("\\s+");
        System.out.println();
        Text tempText = new Text();
        double maxWordWidth = 0;

        // Encontrar a maior largura de palavra
        for (String word : words) {
            System.out.println(word);
            tempText.setFont(new Font(fontSize));
            tempText.setText(word);
            tempText.applyCss(); // Força o cálculo do estilo

            double width = tempText.getLayoutBounds().getWidth()+18;
            System.out.println("Word width: "+width);
            if (width > maxWordWidth) {
                maxWordWidth = width;
            }
        }

        // Reduz a fonte até caber ou atingir o mínimo
        while (maxWordWidth > maxWidth && fontSize > 6) {
            fontSize -= 1;
            maxWordWidth = 0;
            for (String word : words) {
                tempText.setFont(new Font(fontSize));
                tempText.setText(word);
                tempText.applyCss();

                double width = tempText.getLayoutBounds().getWidth()+18;
                System.out.println("Word new width: " + word +" - " +width);
                if (width > maxWordWidth) {
                    maxWordWidth = width;
                }
            }
        }

        cardBtn.setFont(new Font(fontSize));
        tempText.setText(cardBtn.getText().replaceAll("\\s+",""));
        if(tempText.getLayoutBounds().getWidth()+20 > maxWidth) cardBtn.setWrapText(true);

    }

    public void resetGame(){
        cardService.getDiscardDeck().clear();
        playerService.resetEffects();
        gameService.resetEffects();
        enemyService.setMoveControl(0);
    }

    public void loadMenu(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/MainMenu.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent menu = fxmlLoader.load();

        resetGame();

        Stage stage = (Stage) clickedButton.getScene().getWindow();
        Scene scene = new Scene(menu);
        stage.setScene(scene);
    }

}
