package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.service.CardService;
import com.darffin.service.PlayerProgressService;
import com.darffin.service.PlayerService;
import javafx.animation.KeyFrame;
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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class StoreController {
    @Autowired
    private ApplicationContext context;
    @FXML
    private Label playerGold;
    @FXML
    private Button buyCard1, buyCard2, buyCard3;
    @FXML
    private Button back;
    @FXML
    private Label labelBuyCard1, labelBuyCard2, labelBuyCard3;
    @FXML
    private Button buyAbility1, buyAbility2;
    @FXML
    private Button buyDeleteCard;

    @Autowired
    private CardService cardService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerProgressService playerProgressService;
    private List<Card> availableCards;

    public void initialize(){
        playerGold.setText("Gold: " + playerService.playerGold());
        availableCards = cardService.getAllCards();

        List<Card> possibleCard1 = new ArrayList<>();
        List<Card> possibleCard2 = new ArrayList<>();
        List<Card> possibleCard3 = new ArrayList<>();

        List<Card> commons = new ArrayList<>();
        List<Card> rares = new ArrayList<>();
        List<Card> epics = new ArrayList<>();
        List<Card> legendaries = new ArrayList<>();

        for (Card card : availableCards) {
            switch (card.getRarity()) {
                case "Common": commons.add(card); break;
                case "Rare": rares.add(card); break;
                case "Epic": epics.add(card); break;
                case "Legendary": legendaries.add(card); break;
            }
        }

        possibleCard1.addAll(getRandomCards(commons, 5));
        possibleCard1.addAll(getRandomCards(rares, 4));
        possibleCard1.addAll(getRandomCards(epics, 1));

        possibleCard2.addAll(getRandomCards(commons, 3));
        possibleCard2.addAll(getRandomCards(rares, 4));
        possibleCard2.addAll(getRandomCards(epics, 3));

        possibleCard3.addAll(getRandomCards(commons, 1));
        possibleCard3.addAll(getRandomCards(rares, 3));
        possibleCard3.addAll(getRandomCards(epics, 3));
        possibleCard3.addAll(getRandomCards(legendaries, 3));

        for(Card a : possibleCard1) System.out.print(a.getName()+", ");
        System.out.println("p");
        for(Card a : possibleCard2) System.out.print(a.getName()+", ");
        System.out.println("p");
        for(Card a : possibleCard3) System.out.print(a.getName()+", ");

        int randomIndex = ThreadLocalRandom.current().nextInt(10);
        labelBuyCard1.setText(possibleCard1.get(randomIndex).getName()); buyCard1.setId(possibleCard1.get(randomIndex).getName());
        randomIndex = ThreadLocalRandom.current().nextInt(10);
        labelBuyCard2.setText(possibleCard2.get(randomIndex).getName()); buyCard2.setId(possibleCard2.get(randomIndex).getName());
        randomIndex = ThreadLocalRandom.current().nextInt(10);
        labelBuyCard3.setText(possibleCard3.get(randomIndex).getName()); buyCard3.setId(possibleCard3.get(randomIndex).getName());
    }

    public List<Card> getRandomCards(List<Card> source, int count) {
        Collections.shuffle(source);
        return source.subList(0, Math.min(count, source.size()));
    }

    public void buyCard(ActionEvent event){
        Button button = (Button) event.getSource();
        if(playerService.playerGold() >= cardService.getCardByName(button.getId()).getCost()) {
            playerService.playerDeck().add(cardService.getCardByName(button.getId()));
            playerProgressService.saveProgress(playerService.getPlayer());
            button.setDisable(true);
            button.setText("Obtained!");
            playerService.spendGold(cardService.getCardByName(button.getId()).getCost());
            playerGold.setText("Gold: " + playerService.playerGold());
        } else {
            Timeline notEnoughGold = new Timeline(
                    new KeyFrame(Duration.seconds(0), e -> {button.setTextFill(Color.RED); button.setText("Not enough gold!");}),
                    new KeyFrame(Duration.seconds(0.25), e -> button.setTextFill(Color.BLACK)),
                    new KeyFrame(Duration.seconds(0.5),  e -> button.setTextFill(Color.RED)),
                    new KeyFrame(Duration.seconds(0.75), e -> button.setTextFill(Color.BLACK)),
                    new KeyFrame(Duration.seconds(1),  e -> button.setTextFill(Color.RED)),
                    new KeyFrame(Duration.seconds(1.25), e -> {button.setTextFill(Color.BLACK); button.setText("Buy");})
            );
            notEnoughGold.play();
        }
    }

    public void buyEraseCard(){
        // Only when pop-ups like components are implemented [Soon]
    }


    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/Map.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent map = fxmlLoader.load();

        Stage stage = (Stage) back.getScene().getWindow();
        Scene scene = new Scene(map);
        stage.setScene(scene);
    }

}
