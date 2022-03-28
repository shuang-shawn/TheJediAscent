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
    public void attack(Entity attacker, Entity receiver) { }

    public void defense(Entity character) { }

    public void increaseAttack(Entity character) { }

    public void increaseDefense(Entity character) { }
}
