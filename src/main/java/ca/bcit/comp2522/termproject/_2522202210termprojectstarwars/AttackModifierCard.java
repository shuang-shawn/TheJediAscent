package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import static com.almasb.fxgl.dsl.FXGL.getNotificationService;

public class AttackModifierCard extends Card {
    public AttackModifierCard(int value) {
        super(value);
    }

    @Override
    public void increaseAttack(Character character) {
        character.setAttackModifier(this.getValue());
        getNotificationService().pushNotification(character.displayAttackModifier());
    }
}
