package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

public class Enemy extends Character{
    public Enemy(int hp, int defense, int attackModifier, int defenseModifier) {
        super(hp, defense, attackModifier, defenseModifier);
    }

    @Override
    public String toString() {
        return "Enemy's current HP is " + this.getHp() + '.';
    }
}
