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
        attackCard.attack(enemy,player);
    }

    public void defense(){
        defenseCard.defense(enemy);
    }

    public void attackModifier(){
        attackModifierCard.increaseAttack(enemy);
    }

    public void execute(Entity player){
        actionParam = random.nextInt(0,3);
        if (actionParam == 0) {
            attack(player);
        } else if (actionParam == 1) {
            defense();
        } else {
            attackModifier();
        }
    }
}
