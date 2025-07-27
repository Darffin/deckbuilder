package com.darffin.service;

import com.darffin.model.Enemy;
import com.darffin.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyService {

    private final Enemy enemy = Enemy.getEnemyInstance();
    private final Player player = Player.getInstance();
    private int moveControl;

    public EnemyService(){
    this.moveControl = 0;
    }

    public void damageEnemy(int value){
        enemy.setLife(enemy.getLife() - value);
    }

    public String enemyName(){
        return enemy.getName();
    }

    public int enemyLife(){
        return enemy.getLife();
    }

    public void updateMana(){

    }

    public void atk(int value){
        player.setLife(player.getLife() - value);
    }

    public void heavyAtk(int value){
        player.setLife(player.getLife() - value);
    }

    public void wait(int value){

    }

    public List<String> enemyIntention(){
        return enemy.getEnemyIntention();
    }

    public List<Integer> intentionValue(){
        return enemy.getIntentionValue();
    }

    public int getMoveControl() {
        return moveControl;
    }

    public void setMoveControl(int moveControl) {
        this.moveControl = moveControl;
    }
}
