package com.darffin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/darffin/MainMenu.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Deck Builder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        launch(args);
    }
}