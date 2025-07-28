package com.darffin.model;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private Button button;
    private List<Map> next = new ArrayList<>();
    private boolean visited = false;

    public Map(Button button) {
        this.button = button;
    }

    public void addNext(Map node) {
        next.add(node);
    }

    public List<Map> getNext() {
        return next;
    }

    public Button getButton() {
        return button;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
