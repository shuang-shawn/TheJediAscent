package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class TrooperAnimationComponent extends AnimationComponent {
    private static final String IDLE_ASSET = "TrooperIdle.png";
    private static final String ATTACK_ASSET = "TrooperAttack.png";
    private static final String StrongAttack_ASSET = "DookuStrongAttack.png";
    private final AnimationChannel animIdle;
    private final AnimationChannel animAttack;
    private final AnimationChannel animStrongAttack;
    private final int idleWidth, idleHeight;
    private final double originalX = 500;
    private final double originalY = 205;

    public TrooperAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image(IDLE_ASSET),
                4, 99, 97, Duration.seconds(1), 0, 3);
        animAttack = new AnimationChannel(FXGL.image(ATTACK_ASSET),
                17, 266, 97, Duration.seconds(1), 0, 16);
        animStrongAttack = new AnimationChannel(FXGL.image(StrongAttack_ASSET),
                16, 114, 100, Duration.seconds(1), 0, 15);
        idleWidth = animIdle.getFrameWidth(1);
        idleHeight = animIdle.getFrameHeight(1);
        texture = new AnimatedTexture(animIdle);
        texture.playAnimationChannel(animIdle);
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
        frameCounter = 70;
        adjustPosition(animAttack);
        texture.playAnimationChannel(animAttack);
    }

    public void strongAttackAnimation() {
        frameCounter = 70;
        adjustPosition(animStrongAttack);
        texture.playAnimationChannel(animStrongAttack);
    }
}
