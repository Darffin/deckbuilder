package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.service.CardService;
import com.darffin.service.PlayerProgressService;
import com.darffin.service.PlayerService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class ChestController {
    @Autowired
    private ApplicationContext context;
    @FXML
    private Button grabGold, grabCard, grabAbility;
    @FXML
    private Label labelGold, labelCard, labelAbility;
    @FXML
    private Button back;

    private int goldEarned;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private CardService cardService;
    @Autowired
    private PlayerProgressService playerProgressService;


    public void initialize(){
        goldEarned = ThreadLocalRandom.current().nextInt(25,80);
        labelGold.setText("Gold: " + goldEarned);

        grabCard.setId(cardService.getCardForChest().getName());
        labelCard.setText(grabCard.getId());
    }

    public void grabGold(){
        playerService.earnGold(goldEarned);
        grabGold.setDisable(true);
        grabGold.setText("Obtained!");
        playerProgressService.saveProgress(playerService.getPlayer());
    }

    public void grabCard(){

        playerService.playerDeck().add(cardService.getCardByName(grabCard.getId()));
        grabCard.setDisable(true);
        grabCard.setText("Obtained!");
        playerProgressService.saveProgress(playerService.getPlayer());

    }

    public void grabAbility(){
        // ON HOLD
    }

    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/Map.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent map = fxmlLoader.load();

        Stage stage = (Stage) back.getScene().getWindow();
        Scene scene = new Scene(map);
        stage.setScene(scene);
    }

    public java.util.List<Card> getRandomCards(List<Card> source, int count) {
        Collections.shuffle(source);
        return source.subList(0, Math.min(count, source.size()));
    }

}
