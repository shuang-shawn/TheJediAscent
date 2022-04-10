package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import static com.almasb.fxgl.dsl.FXGL.getNotificationService;

public class AttackCard extends Card {
    public AttackCard(int value) {
        super(value);
    }

    @Override
    public void attack(Entity attacker, Entity receiver) {
        if (attacker.getType() == CharacterType.PLAYER) {
            int attackValue = this.getValue() + attacker.getComponent(PlayerStats.class).getAttackModifier()
                    - receiver.getComponent(EnemyStats.class).getDefense();
            receiver.getComponent(EnemyStats.class).setHP(attackValue);
            getNotificationService().pushNotification(receiver.getComponent(EnemyStats.class).toString());
        } else {
            int attackValue = this.getValue() + attacker.getComponent(EnemyStats.class).getAttackModifier()
                    - receiver.getComponent(PlayerStats.class).getDefense();
            receiver.getComponent(PlayerStats.class).setHP(attackValue);
            getNotificationService().pushNotification(receiver.getComponent(PlayerStats.class).toString());
        }
    }
}
