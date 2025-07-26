package com.darffin.service;

import com.darffin.model.Card;
import com.darffin.model.Enemy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardEffectService {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private EnemyService enemyService;

    public void FireBall(){
        enemyService.damageEnemy(6);
    }

    public void Cumulonimbus(){
        enemyService.damageEnemy(10);
    }

    public void FireDart(){
        enemyService.damageEnemy(2);
    }

    public void WaterSplash(){
        enemyService.damageEnemy(5);
    }

    public void Quagmire(){
        enemyService.damageEnemy(8);
    }

    public void LichWind(){
        enemyService.damageEnemy(13);
    }

    public void InfernoShuriken(){
        enemyService.damageEnemy(11);
    }
}
