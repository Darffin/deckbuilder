package com.darffin.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PlayerProgress {
    @Id
    private Long id = 1L;
    private String lastNodeId;

    @OneToOne(cascade = CascadeType.ALL)
    private Player player = Player.getInstance();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastNodeId() {
        return lastNodeId;
    }

    public void setLastNodeId(String lastNodeId) {
        this.lastNodeId = lastNodeId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
