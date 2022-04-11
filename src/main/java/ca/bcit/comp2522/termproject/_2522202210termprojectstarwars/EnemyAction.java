package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import java.util.Random;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class EnemyAction {
    private final Random random;
    private final Entity enemy;
    private final AttackCard attackCard;
    private final DefenseCard defenseCard;
    private final AttackModifierCard attackModifierCard;
    private int seed;

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
        if (enemy.getType() == CharacterType.DOOKU) {
            DroidAction(player);
        } else if (enemy.getType() == CharacterType.WINDU) {
            WinduAction(player);
        } else if (enemy.getType() == CharacterType.GRIEVOUS) {
            GrievousAction(player);
        } else if (enemy.getType() == CharacterType.TROOPER) {
            TrooperAction(player);
        } else if (enemy.getType() == CharacterType.DROID) {
            DroidAction(player);
        } else {
            GuardAction(player);
        }
    }

    private void DookuAction(Entity player) {
        seed = random.nextInt(0, 4);
        switch (seed) {
            case 0:
                enemy.getComponent(DookuAnimationComponent.class).attackAnimation();
            case 1:
                enemy.getComponent(DookuAnimationComponent.class).attackAnimation();
            case 2:
                enemy.getComponent(DookuAnimationComponent.class).attackAnimation();
            case 3:
                enemy.getComponent(DookuAnimationComponent.class).attackAnimation();
        }
    }

    private void WinduAction(Entity player) {
        seed = random.nextInt(0, 4);
        switch (seed) {
            case 0:
                enemy.getComponent(WinduAnimationComponent.class).attackAnimation();
            case 1:
                enemy.getComponent(WinduAnimationComponent.class).attackAnimation();
            case 2:
                enemy.getComponent(WinduAnimationComponent.class).attackAnimation();
            case 3:
                enemy.getComponent(WinduAnimationComponent.class).attackAnimation();
        }
    }

    private void GrievousAction(Entity player) {
        seed = random.nextInt(0, 4);
        switch (seed) {
            case 0:
                enemy.getComponent(GrievousAnimationComponent.class).attackAnimation();
            case 1:
                enemy.getComponent(GrievousAnimationComponent.class).attackAnimation();
            case 2:
                enemy.getComponent(GrievousAnimationComponent.class).attackAnimation();
            case 3:
                enemy.getComponent(GrievousAnimationComponent.class).attackAnimation();
        }
    }

    private void TrooperAction(Entity player) {
        seed = random.nextInt(0, 2);
        switch (seed) {
            case 0:
                enemy.getComponent(TrooperAnimationComponent.class).attackAnimation();

            case 1:
                enemy.getComponent(TrooperAnimationComponent.class).attackAnimation();

        }
    }

    private void DroidAction(Entity player) {
        seed = random.nextInt(0, 2);
        switch (seed) {
            case 0:
                enemy.getComponent(DroidAnimationComponent.class).attackAnimation();

            case 1:
                enemy.getComponent(DroidAnimationComponent.class).attackAnimation();
        }
    }

    private void GuardAction(Entity player) {
        seed = random.nextInt(0, 2);
        switch (seed) {
            case 0:
                enemy.getComponent(GuardAnimationComponent.class).attackAnimation();

            case 1:
                enemy.getComponent(GuardAnimationComponent.class).attackAnimation();
        }
    }
}
