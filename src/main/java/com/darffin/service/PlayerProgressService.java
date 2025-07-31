package com.darffin.service;

import com.darffin.model.Player;
import com.darffin.repository.PlayerProgressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlayerProgressService {
    @Autowired
    private PlayerProgressRepository progressRepository;

    private Player player = Player.getInstance();


    public Player getProgress() {
        return progressRepository.findById(1L).orElseGet(() -> {
            Player progress = new Player();
            return progressRepository.save(progress);
        });
    }

    public void loadPlayer(Player progress){
        player.setLife(progress.getLife());
        player.setMana(progress.getMana());
        player.setGold(progress.getGold());
        player.setDeckPlayer(progress.getDeckPlayer());
        //player.setShield(progress.getShield());
        //player.setStrength(progress.getStrength());
    }


    public void resetPlayerProgress(){

        //Map progress
        Player progress = getProgress();
        progress.setLastNodeId(null);
        saveProgress(progress);
    }

    public void saveProgress(Player progress) {
        progressRepository.save(progress);
    }

    public void updateLastNode(String nodeId) {
        //Player progress = getProgress();
        player.setLastNodeId(nodeId);
    }

    public String getLastNodeId() {
        return getProgress().getLastNodeId();
    }

    public String getNotSavedNode(){
        return player.getLastNodeId();
    }


}
