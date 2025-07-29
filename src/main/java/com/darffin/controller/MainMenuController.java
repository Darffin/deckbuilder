package com.darffin.controller;

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
    private PlayerController playerController;
    @FXML
    private Button close;
    @FXML
    private Button newGame;
    @Autowired
    private ApplicationContext context; // injeta o contexto Spring

    @FXML
    private void newGame() throws IOException {

        /* Fight arena scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/Fight.fxml"));
        fxmlLoader.setControllerFactory(context::getBean); // Aqui a mágica acontece
        Parent fight = fxmlLoader.load();

        FightController fightController = fxmlLoader.getController();

        fightController.prepareNewGame();

         */

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/Map.fxml"));
        fxmlLoader.setControllerFactory(context::getBean); // Aqui a mágica acontece
        Parent map = fxmlLoader.load();

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
