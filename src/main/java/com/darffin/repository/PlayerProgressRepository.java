package com.darffin.repository;

import com.darffin.model.Player;
import com.darffin.model.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Long> {
    PlayerProgress findByLastNodeId(String lastNodeId);
}
