package com.darffin.service;

import com.darffin.model.Enemy;
import org.springframework.stereotype.Service;

@Service
public class EnemyService {

    private final Enemy enemy = Enemy.getEnemyInstance();

    public EnemyService(){

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
}
