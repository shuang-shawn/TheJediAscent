package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

public class PlayerStats extends CharacterStats {
    private int hp;
    private int defense;
    private int attackModifier;
    private int defenseModifier;


    public PlayerStats(String name, int hp, int defense, int attackModifier, int defenseModifier) {
        super(name, hp, defense, attackModifier, defenseModifier);
    }

    @Override
    public String toString() {
        return "Player's current HP is " + this.getHp() + '.';
    }
}
