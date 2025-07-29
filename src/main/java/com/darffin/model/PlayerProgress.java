package com.darffin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PlayerProgress {
    @Id
    private Long id = 1L;
    private String lastNodeId;

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
}
