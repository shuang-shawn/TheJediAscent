package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import java.util.Random;

public class EnemyAction {
    private final Random random;
    private final Entity enemy;
    private final AttackCard attackCard;
    private final DefenseCard defenseCard;
    private final AttackModifierCard attackModifierCard;

    public EnemyAction(Entity enemy) {
        random = new Random();
        this.enemy = enemy;
        this.attackCard = new AttackCard(1);
        this.defenseCard = new DefenseCard(1);
        this.attackModifierCard = new AttackModifierCard(1);
    }

    public void attack(Entity player) {
        System.out.println("Enemy attacked player.");
        attackCard.attack(enemy, player);
        enemy.getComponent(EnemyAnimation.class).getEnemyAnimationComponent();
    }

    public void defense() {
        System.out.println("Enemy increase defense.");
        defenseCard.defense(enemy);
    }

    public void attackModifier() {
        System.out.println("Enemy increase attack.");
        attackModifierCard.increaseAttack(enemy);
    }

    public void execute(Entity player) {
        if (enemy.getType() == CharacterType.BOSS) {
            bossAction(player);
        } else {
            enemyAction(player);
        }
    }

    public void bossAction(Entity player) {
        int actionParam = random.nextInt(1, 11);
        if (actionParam <= 3) {
            attackModifier();
        } else if (actionParam <= 6) {
            defense();
        } else {
            attack(player);
        }
    }

    public void enemyAction(Entity player) {
        int actionParam = random.nextInt(1, 11);
        if (actionParam <= 3) {
            defense();
        } else {
            attack(player);
        }
    }
}
