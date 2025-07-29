package com.darffin.controller;

import com.darffin.model.PlayerProgress;
import com.darffin.service.PlayerProgressService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.darffin.model.MapNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MapController {
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
    //private PlayerProgress playerProgress = playerProgressService.getProgress();
    @FXML
    public void initialize() {
        System.out.println("Total de nós no mapa: " + map.size());
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
        System.out.println("Rodando applyPlayerProgress()");

        String current = playerProgressService.getProgress().getLastNodeId();
        if (current != null && !current.isEmpty()) {
            MapNode node = map.get(current);
            System.out.println("LastNodeId: " + current);

            if (node != null) {
                System.out.println("Habilitando botão: " + node.getId());
                node.getNext().forEach(n -> n.getButton().setDisable(false));
            }
        } else {
            System.out.println("LastNodeId: " + current);
            map.values().stream()
                    .filter(n -> n.getId().startsWith("1_"))
                    .forEach(n -> n.getButton().setDisable(false));
        }
    }





}
