package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

public class Player extends Character {
    public Player(int hp, int defense, int attackModifier, int defenseModifier) {
        super(hp, defense, attackModifier, defenseModifier);
    }

    @Override
    public String toString() {
        return "Player's current HP is " + this.getHp() + '.';
    }

}
