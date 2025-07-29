package com.darffin.service;

import com.darffin.model.Player;
import com.darffin.model.PlayerProgress;
import com.darffin.repository.PlayerProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlayerProgressService {
    @Autowired
    private PlayerProgressRepository progressRepository;

    private Player player = Player.getInstance();

    public PlayerProgress getProgress() {
        return progressRepository.findById(1L).orElseGet(() -> {
            PlayerProgress progress = new PlayerProgress();
            return progressRepository.save(progress);
        });
    }

    public void loadPlayer(){
        PlayerProgress progress = getProgress();
        player = progress.getPlayer();
    }


    public void resetPlayerProgress(){

        //Map progress
        PlayerProgress progress = getProgress();
        progress.setLastNodeId(null);
        saveProgress(progress);
    }

    public void saveProgress(PlayerProgress progress) {
        progressRepository.save(progress);
    }

    public void updateLastNode(String nodeId) {
        PlayerProgress progress = getProgress();
        progress.setLastNodeId(nodeId);
        saveProgress(progress);
    }

    public String getLastNodeId() {
        return getProgress().getLastNodeId();
    }


}
