package com.darffin.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button close;
    @FXML
    private Button newGame;

    @FXML
    private void newGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/Fight.fxml"));
        Parent fight = fxmlLoader.load();

        Stage stage = (Stage) newGame.getScene().getWindow();
        Scene scene = new Scene(fight);
        stage.setScene(scene);
    }

    @FXML
    private void closeGame() {
        close.setText("Fechando...");

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
