package com.darffin.controller;

import com.darffin.model.Player;
import com.darffin.service.PlayerProgressService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainMenuController {
    @Autowired
    private PlayerProgressService playerProgressService;
    @FXML
    private Button close;
    @FXML
    private Button newGame;
    @FXML
    private Button continueGame;
    @Autowired
    private ApplicationContext context; // injeta o contexto Spring

    private Player player;
    private Player playerProgress;
    public void initialize(){
        if(playerProgressService.getLastNodeId()==null){
            continueGame.setDisable(true);
        }
    }
    @FXML
    private void newGame() throws IOException {

        /* Fight arena scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/Fight.fxml"));
        fxmlLoader.setControllerFactory(context::getBean); // Aqui a mágica acontece
        Parent fight = fxmlLoader.load();

        FightController fightController = fxmlLoader.getController();

        fightController.prepareNewGame();

         */
        playerProgressService.resetPlayerProgress();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/PlayerClass.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent playerClass = fxmlLoader.load();

        Stage stage = (Stage) newGame.getScene().getWindow();
        Scene scene = new Scene(playerClass);
        stage.setScene(scene);

    }
    @FXML
    private void continueGame() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/Map.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent map = fxmlLoader.load();

        playerProgressService.loadPlayer(playerProgressService.getProgress());

        Stage stage = (Stage) newGame.getScene().getWindow();
        Scene scene = new Scene(map);
        stage.setScene(scene);
    }

    @FXML
    private void closeGame() {
        close.setText("Fechando...");

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
