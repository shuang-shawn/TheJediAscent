package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

public class PlayerStats extends CharacterStats {
    private int hp;
    private int defense;
    private int attackModifier;
    private int defenseModifier;


    public PlayerStats(int hp, int defense, int attackModifier, int defenseModifier) {
        super(hp, defense, attackModifier, defenseModifier);
    }

    public String test(){
        return "testing";
    }

    @Override
    public String toString() {
        return "Player's current HP is " + this.getHp() + '.';
    }
}
