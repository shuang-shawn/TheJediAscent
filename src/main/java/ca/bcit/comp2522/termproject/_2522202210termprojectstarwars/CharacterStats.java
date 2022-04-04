package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.component.Component;

public abstract class CharacterStats extends Component {
    private final String name;
    private int hp;
    private int defense;
    private int attackModifier;
    private int defenseModifier;


    public CharacterStats(String name, int hp, int defense, int attackModifier, int defenseModifier) {
        this.name = name;
        this.hp = hp;
        this.defense = defense;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
    }

    public void setHP(int damage) {
        this.hp -= damage - defense;
    }

    public void setDefense(int value) {
        this.defense += value;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier += attackModifier;
    }

    public String getName() {
        return name;
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
