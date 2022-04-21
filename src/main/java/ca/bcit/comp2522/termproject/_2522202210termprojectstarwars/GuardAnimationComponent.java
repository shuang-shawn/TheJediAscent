package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class GuardAnimationComponent extends AnimationComponent {
    private static final String IDLE_ASSET = "GuardIdle.png";
    private static final String ATTACK_ASSET = "GuardAttack.png";
    private static final String StrongAttack_ASSET = "GuardStrongAttack.png";
    private final AnimationChannel animIdle;
    private final AnimationChannel animAttack;
    private final AnimationChannel animStrongAttack;
    private final int idleWidth, idleHeight;
    private final double originalX = 500;
    private final double originalY = 172;

    public GuardAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image(IDLE_ASSET),
                4, 161, 134, Duration.seconds(0.5), 0, 3);
        animAttack = new AnimationChannel(FXGL.image(ATTACK_ASSET),
                7, 251, 136, Duration.seconds(0.5), 0, 6);
        animStrongAttack = new AnimationChannel(FXGL.image(StrongAttack_ASSET),
                11, 423, 190, Duration.seconds(1), 0, 10);
        idleWidth = animIdle.getFrameWidth(1);
        idleHeight = animIdle.getFrameHeight(1);
        texture = new AnimatedTexture(animIdle);
        texture.loopAnimationChannel(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {
        if (frameCounter > 0) {
            frameCounter--;
        }
        if (texture.getAnimationChannel() != animIdle && frameCounter == 0) {
            entity.setX(originalX);
            entity.setY(originalY);
            texture.loopAnimationChannel(animIdle);
        }
    }

    public void adjustPosition(AnimationChannel anim) {
        entity.setX(originalX - (int) ((anim.getFrameWidth(1) - idleWidth) / 2));
        entity.setY(originalY - (anim.getFrameHeight(1) - idleHeight));
    }

    public void attackAnimation() {
        frameCounter = 35;
        adjustPosition(animAttack);
        texture.playAnimationChannel(animAttack);
    }

    public void strongAttackAnimation() {
        frameCounter = 70;
        adjustPosition(animStrongAttack);
        texture.playAnimationChannel(animStrongAttack);
    }
}
