package com.darffin.service;

import com.darffin.model.Player;
import com.darffin.repository.PlayerProgressRepository;
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
        player.setShield(progress.getShield());
        player.setStrength(progress.getStrength());
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
        Player progress = getProgress();
        progress.setLastNodeId(nodeId);
        saveProgress(progress);
    }

    public String getLastNodeId() {
        return getProgress().getLastNodeId();
    }


}
