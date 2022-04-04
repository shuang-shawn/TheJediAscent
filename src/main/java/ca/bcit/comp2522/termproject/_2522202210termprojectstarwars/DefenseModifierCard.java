package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import static com.almasb.fxgl.dsl.FXGL.getNotificationService;

public class DefenseModifierCard extends Card {
    public DefenseModifierCard(int value) {
        super(value);
    }

    @Override
    public void increaseDefense(Entity character) {
        if (character.getType() == CharacterType.PLAYER) {
            character.getComponent(PlayerStats.class).setDefenseModifier(this.getValue());
            getNotificationService()
                    .pushNotification(character.getComponent(PlayerStats.class).displayDefenseModifier());
        } else {
            character.getComponent(EnemyStats.class).setDefenseModifier(this.getValue());
            getNotificationService()
                    .pushNotification(character.getComponent(EnemyStats.class).displayDefenseModifier());
        }
    }
}