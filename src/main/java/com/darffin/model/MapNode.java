package com.darffin.model;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class MapNode {
    private String id;
    private int level;
    private Button button;
    private List<MapNode> next = new ArrayList<>();
    private boolean enabled = false;



    public MapNode(String id, Button button) {
        this.id = id;
        this.button = button;
        this.enabled = false;
        this.button.setDisable(true);

    }

    public void addNext(MapNode node) {
        next.add(node);
    }

    public void addNext(MapNode node, MapNode node2) {
        next.add(node);
        next.add(node2);
    }

    public void addNext(MapNode node, MapNode node2, MapNode node3) {
        next.add(node);
        next.add(node2);
        next.add(node3);
    }


    public List<MapNode> getNext() {
        return next;
    }

    public Button getButton() {
        return button;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean bool) {
        this.enabled = bool;
        this.button.setDisable(!bool); // Idk if this will work lol
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
