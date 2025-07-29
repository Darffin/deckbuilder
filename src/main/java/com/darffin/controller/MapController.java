package com.darffin.controller;

import com.darffin.model.PlayerProgress;
import com.darffin.service.EnemyService;
import com.darffin.service.PlayerProgressService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.darffin.model.MapNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MapController {
    @Autowired
    private ApplicationContext context;
    @FXML
    private Button btn1Lv1;
    @FXML
    private Button btn2Lv1;
    @FXML
    private Button btn3Lv1;
    @FXML
    private Button btn4Lv1;
    @FXML
    private Button btn1Lv2;
    @FXML
    private Button btn2Lv2;
    @FXML
    private Button btn3Lv2;
    @FXML
    private Button btn1Lv3;
    @FXML
    private Button btn2Lv3;
    @FXML
    private Button btn1Lv4;
    @FXML
    private Button btn2Lv4;
    @FXML
    private Button btn3Lv4;
    @FXML
    private Button btn4Lv4;
    @FXML
    private Button btn5Lv4;
    @FXML
    private Button boss;

    private Map<String, MapNode> map = new HashMap<>(); // In future updates, remove interaction between controller and model(MapNode)
    @Autowired
    private PlayerProgressService playerProgressService;
    @Autowired
    private EnemyService enemyService;
    //private PlayerProgress playerProgress = playerProgressService.getProgress();
    @FXML
    public void initialize() {
        map.put("1_1", new MapNode("1_1", btn1Lv1));
        map.put("1_2", new MapNode("1_2", btn2Lv1));
        map.put("1_3", new MapNode("1_3", btn3Lv1));
        map.put("1_4", new MapNode("1_4", btn4Lv1));
        map.put("2_1", new MapNode("2_1", btn1Lv2));
        map.put("2_2", new MapNode("2_2", btn2Lv2));
        map.put("2_3", new MapNode("2_3", btn3Lv2));
        map.put("3_1", new MapNode("3_1", btn1Lv3));
        map.put("3_2", new MapNode("3_2", btn2Lv3));
        map.put("4_1", new MapNode("4_1", btn1Lv4));
        map.put("4_2", new MapNode("4_2", btn2Lv4));
        map.put("4_3", new MapNode("4_3", btn3Lv4));
        map.put("4_4", new MapNode("4_4", btn4Lv4));
        map.put("4_5", new MapNode("4_5", btn5Lv4));
        map.put("boss", new MapNode("boss", boss));

        // Level 1
        map.get("1_1").addNext(map.get("2_1"));
        map.get("1_2").addNext(map.get("2_2"));
        map.get("1_3").addNext(map.get("2_2"));
        map.get("1_4").addNext(map.get("2_3"));

        // Level 2
        map.get("2_1").addNext(map.get("3_1"));
        map.get("2_2").addNext(map.get("3_2"));
        map.get("2_3").addNext(map.get("3_2"));

        // Level 3
        map.get("3_1").addNext(map.get("4_1"), map.get("4_2"));
        map.get("3_2").addNext(map.get("4_3"), map.get("4_4"), map.get("4_5"));


        // Level 4 (All paths lead to boss)
        for(MapNode a : map.values()){
            if (a.getId().matches("4_.*")){
                a.addNext(map.get("boss"));
            }
        }

        for (MapNode node : map.values()) {
            node.getButton().setDisable(true);
        }

        applyPlayerProgress();

    }

    private void applyPlayerProgress() {
        String current = playerProgressService.getProgress().getLastNodeId();
        if (current != null && !current.isEmpty()) {
            MapNode node = map.get(current);
            System.out.println("LastNodeId: " + current);

            if (node != null) {
                node.getNext().forEach(n -> n.getButton().setDisable(false));
            }
        } else {
            System.out.println("LastNodeId: " + current);
            map.values().stream()
                    .filter(n -> n.getId().startsWith("1_"))
                    .forEach(n -> n.getButton().setDisable(false));
        }
    }





    public void loadFight(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        String nodeId = clickedButton.getId();

        MapNode node = map.get(nodeId);
        System.out.println("Node clicado: " + node);
        if (node != null) {
            node.getNext().forEach(n -> n.getButton().setDisable(false));
            node.getNext().forEach(n -> System.out.println("Node next: " + n.getId()));
        }
        playerProgressService.updateLastNode(nodeId);

        enemyService.enemyLizard(); // Update to other methods

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/darffin/fxml/Fight.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent fight = fxmlLoader.load();

        FightController fightController = fxmlLoader.getController();

        fightController.prepareNewGame();
        Stage stage = (Stage) clickedButton.getScene().getWindow();
        Scene scene = new Scene(fight);
        stage.setScene(scene);




    }





}
