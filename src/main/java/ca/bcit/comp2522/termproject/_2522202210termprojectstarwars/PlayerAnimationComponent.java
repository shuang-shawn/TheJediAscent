package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class PlayerAnimationComponent extends AnimationComponent {
    private static final String IDLE_ASSET = "AnakinIdle.png";
    private static final String RUN_ASSET = "AnakinRun.png";
    private static final String ATTACK_ASSET = "AnakinAttack.png";
    private static final String Defense_ASSET = "AnakinDefense.png";
    private static final String AttackModifier_ASSET = "AnakinAttackModifier.png";
    private final AnimationChannel animRun;
    private final AnimationChannel animIdle;
    private final AnimationChannel animAttack;
    private final AnimationChannel animDefense;
    private final AnimationChannel animAttackModifier;
    private final int idleWidth, idleHeight;
    private final double originalX = 200;
    private final double originalY = 170;

    public PlayerAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image(IDLE_ASSET),
                5, 94, 132, Duration.seconds(1), 0, 4);
        animRun = new AnimationChannel(FXGL.image(RUN_ASSET),
                13, 124, 130, Duration.seconds(1.5), 0, 12);
        animAttack = new AnimationChannel(FXGL.image(ATTACK_ASSET),
                15, 172, 136, Duration.seconds(1), 0, 14);
        animDefense = new AnimationChannel(FXGL.image(Defense_ASSET),
                3, 72, 132, Duration.seconds(0.2), 0, 2);
        animAttackModifier = new AnimationChannel(FXGL.image(AttackModifier_ASSET),
                12, 76, 130, Duration.seconds(1), 0, 11);
        idleWidth = animIdle.getFrameWidth(1);
        idleHeight = animIdle.getFrameHeight(1);
        texture = new AnimatedTexture(animIdle);
        texture.playAnimationChannel(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {
        if (entity.getX() < 200 && frameCounter == 0) {
            entity.translateX(speed * tpf);
            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animRun);
            }
        } else {
            if (frameCounter > 0) {
                frameCounter--;
            }
            if (texture.getAnimationChannel() != animIdle && frameCounter == 0) {
                entity.setX(originalX);
                entity.setY(originalY);
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    public void adjustPosition(AnimationChannel anim) {
        entity.setX(originalX - (int)((anim.getFrameWidth(1) - idleWidth) / 2));
        entity.setY(originalY - (anim.getFrameHeight(1) - idleHeight));
    }

    public void attackAnimation() {
        frameCounter = 70;
        adjustPosition(animAttack);
        texture.playAnimationChannel(animAttack);
    }

    public void defenseAnimation() {
        frameCounter = 100;
        adjustPosition(animDefense);
        texture.playAnimationChannel(animDefense);
    }

    public void buffAnimation() {
        frameCounter = 100;
        adjustPosition(animAttackModifier);
        texture.playAnimationChannel(animAttackModifier);
    }
}
