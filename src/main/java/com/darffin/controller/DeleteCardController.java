package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.service.CardService;
import com.darffin.service.PlayerProgressService;
import com.darffin.service.PlayerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteCardController {
    @FXML
    private Button card1, card2, card3, card4, card5, card6, card7, card8;
    @FXML
    private Label card1Effect, card2Effect, card3Effect, card4Effect, card5Effect, card6Effect, card7Effect, card8Effect;
    @FXML
    private Button btnPrevious, btnNext;
    @FXML
    private Label previousCount, nextCount;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private CardService cardService;
    @Autowired
    private PlayerProgressService playerProgressService;

    private Stage stage;

    private List<Card> previousCards = new ArrayList<>();
    private List<Card> allPlayerCards = new ArrayList<>();
    private List<Card> nextCards = new ArrayList<>();

    public void initialize(){
        allPlayerCards.addAll(playerService.getPlayer().getDeckPlayer());

        for(Card a : allPlayerCards) System.out.print(a.getName()+", ");
        loadCards(allPlayerCards);


        nextCards.addAll(allPlayerCards);
        allPlayerCards.clear();
        if(nextCards.isEmpty()) btnNext.setDisable(true);
        btnPrevious.setDisable(true);

        updateLabel();
    }

    public void updateLabel(){
        previousCount.setText(previousCards.size()+" / "+playerService.playerDeck().size());
        nextCount.setText(nextCards.size()+" / "+playerService.playerDeck().size());
    }

    public void loadCards(List<Card> cards){
        if(verifyIfNull(card1, card1Effect, cards)) {
            card1.setText(cards.get(0).getName());
            card1Effect.setText(cards.get(0).getEffect());
            resizeFontCard(card1);
            cards.remove(0);
        }

        if(verifyIfNull(card2, card2Effect, cards)) {
            card2.setText(cards.get(0).getName());
            card2Effect.setText(cards.get(0).getEffect());
            resizeFontCard(card2);
            cards.remove(0);
        }

        if(verifyIfNull(card3, card3Effect, cards)) {
            card3.setText(cards.get(0).getName());
            card3Effect.setText(cards.get(0).getEffect());
            resizeFontCard(card3);
            cards.remove(0);
        }

        if(verifyIfNull(card4, card4Effect, cards)) {
            card4.setText(cards.get(0).getName());
            card4Effect.setText(cards.get(0).getEffect());
            resizeFontCard(card4);
            cards.remove(0);
        }

        if(verifyIfNull(card5, card5Effect, cards)) {
            card5.setText(cards.get(0).getName());
            card5Effect.setText(cards.get(0).getEffect());
            resizeFontCard(card5);
            cards.remove(0);
        }

        if(verifyIfNull(card6, card6Effect, cards)) {
            card6.setText(cards.get(0).getName());
            card6Effect.setText(cards.get(0).getEffect());
            resizeFontCard(card6);
            cards.remove(0);
        }

        if(verifyIfNull(card7, card7Effect, cards)) {
            card7.setText(cards.get(0).getName());
            card7Effect.setText(cards.get(0).getEffect());
            resizeFontCard(card7);
            cards.remove(0);
        }

        if(verifyIfNull(card8, card8Effect, cards)) {
            card8.setText(cards.get(0).getName());
            card8Effect.setText(cards.get(0).getEffect());
            resizeFontCard(card8);
            cards.remove(0);
        }

    }

    public boolean verifyIfNull(Button n, Label l, List<Card> c){
        if(!c.isEmpty()){
            n.setVisible(true);
            return true;
        }
        l.setText("");
        n.setText("");
        n.setVisible(false);
        return false;
    }

    public void addToChosenList(List<Card> c){
        if(!card1.getText().isEmpty()) c.add(cardService.getCardByName(card1.getText()));
        if(!card2.getText().isEmpty()) c.add(cardService.getCardByName(card2.getText()));
        if(!card3.getText().isEmpty()) c.add(cardService.getCardByName(card3.getText()));
        if(!card4.getText().isEmpty()) c.add(cardService.getCardByName(card4.getText()));
        if(!card5.getText().isEmpty()) c.add(cardService.getCardByName(card5.getText()));
        if(!card6.getText().isEmpty()) c.add(cardService.getCardByName(card6.getText()));
        if(!card7.getText().isEmpty()) c.add(cardService.getCardByName(card7.getText()));
        if(!card8.getText().isEmpty()) c.add(cardService.getCardByName(card8.getText()));
    }

    public void previousCards(){
        addToChosenList(nextCards);

        loadCards(previousCards);
        if(previousCards.isEmpty()) btnPrevious.setDisable(true);
        if(!nextCards.isEmpty()) btnNext.setDisable(false);
        updateLabel();
    }

    public void nextCards(){
        addToChosenList(previousCards);

        loadCards(nextCards);
        if(nextCards.isEmpty()) btnNext.setDisable(true);
        if(!previousCards.isEmpty()) btnPrevious.setDisable(false);
        updateLabel();
    }

    public void deleteCard(ActionEvent e){
        Button clickedButton = (Button) e.getSource();

        int index = -1;
        for(Card a : playerService.playerDeck()) {
            if (a.getName().equals(clickedButton.getText())) {
                index = playerService.playerDeck().indexOf(a);
                break;
            }
        }
        playerService.playerDeck().remove(index);

        playerProgressService.saveProgress(playerService.getPlayer());

        nextCards.clear();
        previousCards.clear();
        onClose();
    }

    public void onClose(){
        if(stage != null) stage.close();
    }

    public void setStage(Stage s){
        this.stage = s;
    }

    public void resizeFontCard(Button cardBtn) {
        double maxWidth = 100;
        double fontSize = 11;

        String[] words = cardBtn.getText().split("\\s+");
        System.out.println();
        Text tempText = new Text();
        double maxWordWidth = 0;

        for (String word : words) {
            System.out.println(word);
            tempText.setFont(new Font(fontSize));
            tempText.setText(word);
            tempText.applyCss();

            double width = tempText.getLayoutBounds().getWidth()+18;
            System.out.println("Word width: "+width);
            if (width > maxWordWidth) {
                maxWordWidth = width;
            }
        }


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

}
