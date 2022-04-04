package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import java.util.Random;

public class EnemyAction {
    private int actionParam;
    private final Random random;
    private Entity enemy;
    private final AttackCard attackCard;
    private final DefenseCard defenseCard;
    private final AttackModifierCard attackModifierCard;

    public EnemyAction(Entity enemy){
        random = new Random();
        this.enemy = enemy;
        this.attackCard = new AttackCard(10);
        this.defenseCard = new DefenseCard(5);
        this.attackModifierCard = new AttackModifierCard(1);
    }

    public void attack(Entity player){
        System.out.println("Enemy attacked player.");
        attackCard.attack(enemy,player);
    }

    public void defense(){
        System.out.println("Enemy increase defense.");
        defenseCard.defense(enemy);
    }

    public void attackModifier(){
        System.out.println("Enemy increase attack.");
        attackModifierCard.increaseAttack(enemy);
    }

    public void execute(Entity player){
        actionParam = random.nextInt(1,11);
        if (actionParam <= 6) {
            attack(player);
        } else if (actionParam <= 8) {
            defense();
        } else {
            attackModifier();
        }
    }
}
