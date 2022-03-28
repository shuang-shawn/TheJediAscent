package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import static com.almasb.fxgl.dsl.FXGL.getNotificationService;

public class AttackModifierCard extends Card {
    public AttackModifierCard(int value) {
        super(value);
    }

    @Override
    public void increaseAttack(Entity character) {
        if (character.getType() == CharacterType.PLAYER) {
            character.getComponent(PlayerStats.class).setAttackModifier(this.getValue());
            getNotificationService()
                    .pushNotification(character.getComponent(PlayerStats.class).displayAttackModifier());
        } else {
            character.getComponent(EnemyStats.class).setAttackModifier(this.getValue());
            getNotificationService()
                    .pushNotification(character.getComponent(EnemyStats.class).displayAttackModifier());
        }
    }
}
