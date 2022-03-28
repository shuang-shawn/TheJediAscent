package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import static com.almasb.fxgl.dsl.FXGL.getNotificationService;

public class AttackCard extends Card {
    public AttackCard(int value) {
        super(value);
    }

    @Override
    public void attack(Character attacker, Character receiver) {
        int attackValue = this.getValue() + attacker.getAttackModifier() - receiver.getDefenseModifier();
        receiver.setHP(attackValue);
        getNotificationService().pushNotification(receiver.toString());
    }
}
