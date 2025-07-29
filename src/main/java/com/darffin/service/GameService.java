package com.darffin.service;

import com.darffin.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private int mana;

    private final Player player = Player.getInstance();

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void reloadMana(){
        this.mana = player.getMana();
    }
}
