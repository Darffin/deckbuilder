package com.darffin.model;

import java.util.ArrayList;
import java.util.List;

public class Enemy {
    private int life;
    private String name;

    private List<String> enemyIntention = new ArrayList<String>();
    private List<Integer> intentionValue = new ArrayList<Integer>();
    private static Enemy enemy;

    public Enemy(){
        this.ForestLizard();
    }

    public void ForestLizard(){
        this.life = 200;
        this.name = "Forest Lizard";

        this.enemyIntention.add("atk"); this.intentionValue.add(8);
        this.enemyIntention.add("heavyAtk"); this.intentionValue.add(15);
        this.enemyIntention.add("wait"); this.intentionValue.add(1);
        this.enemyIntention.add("heavyAtk"); this.intentionValue.add(15);
        this.enemyIntention.add("wait"); this.intentionValue.add(1);
        this.enemyIntention.add("atk"); this.intentionValue.add(8);
    }

    public void MoonGhost(){
        this.life = 90;
        this.name = "Moon Ghost";

        this.enemyIntention.add("wait"); this.intentionValue.add(1);
        this.enemyIntention.add("wait"); this.intentionValue.add(1);
        this.enemyIntention.add("heavyAtk"); this.intentionValue.add(18);
        this.enemyIntention.add("heavyAtk"); this.intentionValue.add(18);
        this.enemyIntention.add("wait"); this.intentionValue.add(1);
        this.enemyIntention.add("wait"); this.intentionValue.add(8);
    }

    public void SunLancer(){
        this.life = 150;
        this.name = "Sun Lancer";

        this.enemyIntention.add("atk"); this.intentionValue.add(8);
        this.enemyIntention.add("atk"); this.intentionValue.add(15);
        this.enemyIntention.add("atk"); this.intentionValue.add(1);
        this.enemyIntention.add("atk"); this.intentionValue.add(15);
        this.enemyIntention.add("wait"); this.intentionValue.add(1);
        this.enemyIntention.add("heavyAtk"); this.intentionValue.add(8);
    }

    public void DwarfForest(){
        this.life = 90;
        this.name = "Dwarf Forest";

        this.enemyIntention.add("atk"); this.intentionValue.add(4);
        this.enemyIntention.add("atk"); this.intentionValue.add(6);
        this.enemyIntention.add("atk"); this.intentionValue.add(10);
        this.enemyIntention.add("atk"); this.intentionValue.add(12);
        this.enemyIntention.add("atk"); this.intentionValue.add(15);
        this.enemyIntention.add("wait"); this.intentionValue.add(1);
    }

    public static Enemy getEnemyInstance(){
        if (enemy == null) enemy = new Enemy();
        return enemy;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public List<Integer> getIntentionValue() {
        return intentionValue;
    }

    public void setIntentionValue(List<Integer> intentionValue) {
        this.intentionValue = intentionValue;
    }

    public List<String> getEnemyIntention() {
        return enemyIntention;
    }

    public void setEnemyIntention(List<String> enemyIntention) {
        this.enemyIntention = enemyIntention;
    }


}
