package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

public abstract class Character extends Entity {
    private int hp;
    private int defense;
    private int attackModifier;
    private int defenseModifier;


    public Character(int hp, int defense, int attackModifier, int defenseModifier) {
        this.hp = hp;
        this.defense = defense;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
    }

    public void setHP(int damage) {
        if (damage <= defense) {
            this.defense -= damage;
        } else {
            this.defense = 0;
            this.hp -= damage - defense;
        }
    }
    public void setDefense(int value) {
        this.defense += value;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier += attackModifier;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public int getHp() {
        return hp;
    }

    public String displayDefense() {
        return "Defense: " + this.defense;
    }
    public String displayAttackModifier() {
        return "Attack modifier: " + this.attackModifier;
    }
    @Override
    public String toString() {
        return "Character{" + "hp=" + hp + '}';
    }
}
