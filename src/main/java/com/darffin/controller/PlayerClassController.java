package com.darffin.controller;

import com.darffin.service.PlayerProgressService;
import com.darffin.service.PlayerService;
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
public class PlayerClassController {
    @Autowired
    private ApplicationContext context;
    @FXML
    private Button lunaBtn;
    @FXML
    private Button solanoBtn;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerProgressService playerProgressService;

    public void chooseSolano() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/Map.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent map = fxmlLoader.load();

        playerService.Solano();
        playerProgressService.saveProgress(playerService.getPlayer());

        Stage stage = (Stage) solanoBtn.getScene().getWindow();
        Scene scene = new Scene(map);
        stage.setScene(scene);
    }

    public void chooseLuna() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/Map.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent map = fxmlLoader.load();

        playerService.Luna();
        playerProgressService.saveProgress(playerService.getPlayer());

        Stage stage = (Stage) lunaBtn.getScene().getWindow();
        Scene scene = new Scene(map);
        stage.setScene(scene);
    }
}
