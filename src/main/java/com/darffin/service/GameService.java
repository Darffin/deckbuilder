package com.darffin.service;

import com.darffin.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private int mana;
    private int burn = 0;
    private int poison = 0;

    private final Player player = Player.getInstance();

    @Autowired
    private EnemyService enemyService;

    public void applyGameEffect(String enemyTurnStage){

        if(enemyTurnStage.equals("end")) { // Only after Enemy move
            if (burn > 0) {
                enemyService.damageEnemy(this.burn);
                burn--;
            }
        }

    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void reloadMana(){
        this.mana = player.getMana();
    }

    public int getBurn() {
        return burn;
    }

    public void setBurn(int burn) {
        this.burn = burn;
    }

    public int getPoison() {
        return poison;
    }

    public void setPoison(int poison) {
        this.poison = poison;
    }
}
