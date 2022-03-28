package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

public abstract class Card extends Entity {
    private int value;

    public Card(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public void attack(Character attacker, Character receiver) { }

    public void defense(Character character) { }

    public void increaseAttack(Character character) { }

    public void increaseDefense(Character character) { }
}
