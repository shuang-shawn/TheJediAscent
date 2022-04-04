package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

public class PlayerStats extends CharacterStats {
    public PlayerStats(String name, int hp, int defense, int attackModifier, int defenseModifier) {
        super(name, hp, defense, attackModifier, defenseModifier);
    }

    @Override
    public String toString() {
        return "Player's current HP is " + this.getHp() + '.';
    }
}
