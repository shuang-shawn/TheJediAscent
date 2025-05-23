package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class DroidAnimationComponent extends AnimationComponent {
    private static final String IDLE_ASSET = "DroidIdle.png";
    private static final String ATTACK_ASSET = "DroidAttack.png";
    private static final String StrongAttack_ASSET = "DroidStrongAttack.png";
    private final AnimationChannel animIdle;
    private final AnimationChannel animAttack;
    private final AnimationChannel animStrongAttack;
    private final int idleWidth, idleHeight;
    private final double originalX = 500;
    private final double originalY = 168;

    public DroidAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image(IDLE_ASSET),
                2, 142, 130, Duration.seconds(0.5), 0, 1);
        animAttack = new AnimationChannel(FXGL.image(ATTACK_ASSET),
                7, 176, 127, Duration.seconds(0.5), 0, 6);
        animStrongAttack = new AnimationChannel(FXGL.image(StrongAttack_ASSET),
                13, 161, 175, Duration.seconds(0.5), 0, 12);
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
        frameCounter = 35;
        adjustPosition(animStrongAttack);
        texture.playAnimationChannel(animStrongAttack);
    }
}
