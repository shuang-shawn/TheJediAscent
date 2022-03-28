package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import static com.almasb.fxgl.dsl.FXGL.getNotificationService;

public class DefenseCard extends Card {
    public DefenseCard(int value) {
        super(value);
    }

    @Override
    public void defense(Entity character) {
        if (character.getType() == CharacterType.PLAYER) {
            character.getComponent(PlayerStats.class).setDefense(this.getValue());
            getNotificationService().pushNotification(character.getComponent(PlayerStats.class).displayDefense());
        } else {
            character.getComponent(EnemyStats.class).setDefense(this.getValue());
            getNotificationService().pushNotification(character.getComponent(EnemyStats.class).displayDefense());
        }
    }
}
