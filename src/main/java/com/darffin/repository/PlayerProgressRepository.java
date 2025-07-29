package com.darffin.repository;

import com.darffin.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerProgressRepository extends JpaRepository<Player, Long> {
    Player findByLastNodeId(String lastNodeId);
    Player findByLife(int life);
    Player findByMana(int mana);
    Player findByShield(int shield);
    Player findByStrength(int strength);
}
