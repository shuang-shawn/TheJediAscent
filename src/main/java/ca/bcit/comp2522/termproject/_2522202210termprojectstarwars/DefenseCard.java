package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import static com.almasb.fxgl.dsl.FXGL.getNotificationService;

public class DefenseCard extends Card {
    public DefenseCard(int value) {
        super(value);
    }

    @Override
    public void defense(Character character) {
        character.setDefense(this.getValue());
        getNotificationService().pushNotification(character.displayDefense());
    }
}
