package com.darffin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardEffectService {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private EnemyService enemyService;
    @Autowired
    private GameService gameService;

    // ---------------- *** Common Cards [8] *** ----------------

    public void FireBall(){
        enemyService.damageEnemy(6);
        gameService.setBurn(gameService.getBurn() + 2);
    }

    public void AncestralStrength(){
        playerService.giveStrength(4);
    }

    public void FireDart(){
        enemyService.damageEnemy(2);
        gameService.setBurn(gameService.getBurn() + 1);
    }

    public void WaterSplash(){
        enemyService.damageEnemy(5);
    }

    public void Quagmire(){
        enemyService.damageEnemy(5);
        gameService.setPoison(gameService.getPoison() + 2);
    }

    public void PoisonArrow(){
        gameService.setPoison(gameService.getPoison() + 2);
        enemyService.damageEnemy(gameService.getPoison());
        gameService.setPoison(0);
    }

    public void HighShield(){
        playerService.giveShield(8);
    }

    public void ShieldThrow(){
        enemyService.damageEnemy(playerService.shield());
        playerService.giveShield(-playerService.shield()); // Maybe not :)
    }

    // ---------------- *** Rare Cards [4] *** ----------------

    public void InoffensiveFrog(){
        gameService.setPoison(gameService.getPoison() + 8);
        enemyService.damageEnemy(gameService.getPoison());
        gameService.setPoison(0);
    }

    public void FuelToFlames(){
        gameService.setBurn(gameService.getBurn() + 8);
    }

    public void InfernoShuriken(){
        enemyService.damageEnemy(6);
        gameService.setBurn(gameService.getBurn() + 3);
    }

    public void LegalAnabolic(){
        playerService.giveStrength(8);
    }

    // ---------------- *** Epic Cards [4] *** ----------------

    public void ManaBeacon(){
        gameService.setMana(gameService.getMana() + 2);
    }

    public void StrategicAttack(){
        enemyService.damageEnemy(5);
        playerService.giveShield(5);
    }

    public void LichWind(){
        enemyService.damageEnemy(5);
        gameService.setPoison(gameService.getPoison() + 13);
    }

    public void ShieldBash(){
        enemyService.damageEnemy(playerService.shield());
    }

    // ---------------- *** Legendary Cards [4] *** ----------------

    public void HazardWorld(){
        gameService.setPoison(gameService.getPoison() * 2);
        enemyService.damageEnemy(gameService.getPoison());
        gameService.setPoison(0);
    }

    public void AdrenalineSpike(){
        playerService.damagePlayer(8);
        playerService.giveStrength((playerService.strength() * 2) - playerService.strength());
    }

    public void StoneDome(){
        playerService.giveShield(20);
    }

    public void Cumulonimbus(){
        enemyService.damageEnemy(10);
        playerService.giveStrength(5);
    }

}
