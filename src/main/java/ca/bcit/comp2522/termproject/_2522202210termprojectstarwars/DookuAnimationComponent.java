package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class DookuAnimationComponent extends AnimationComponent {
    private static final String IDLE_ASSET = "DookuIdle.png";
    private static final String ATTACK_ASSET = "DookuAttack.png";
    private static final String BLOCK_ASSET = "DookuBlock.png";
    private static final String StrongAttack_ASSET = "DookuStrongAttack.png";
    private static final String AttackModifier_ASSET = "DookuAttackModifier.png";
    private final AnimationChannel animIdle;
    private final AnimationChannel animAttack;
    private final AnimationChannel animBlock;
    private final AnimationChannel animStrongAttack;
    private final AnimationChannel animAttackModifier;
    private final int idleWidth, idleHeight;
    private final double originalX = 500;
    private final double originalY = 170;

    public DookuAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image(IDLE_ASSET),
                7, 96, 132, Duration.seconds(1), 0, 6);
        animAttack = new AnimationChannel(FXGL.image(ATTACK_ASSET),
                9, 224, 185, Duration.seconds(0.5), 0, 8);
        animBlock = new AnimationChannel(FXGL.image(BLOCK_ASSET),
                3, 105, 131, Duration.seconds(0.5), 0, 2);
        animStrongAttack = new AnimationChannel(FXGL.image(StrongAttack_ASSET),
                16, 245, 214, Duration.seconds(1), 0, 15);
        animAttackModifier = new AnimationChannel(FXGL.image(AttackModifier_ASSET),
                10, 99, 111, Duration.seconds(1), 0, 9);
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

    public void blockAnimation() {
        frameCounter = 35;
        adjustPosition(animBlock);
        texture.playAnimationChannel(animBlock);
    }

    public void strongAttackAnimation() {
        frameCounter = 70;
        adjustPosition(animStrongAttack);
        texture.playAnimationChannel(animStrongAttack);
    }

    public void buffAnimation() {
        frameCounter = 70;
        adjustPosition(animAttackModifier);
        texture.playAnimationChannel(animAttackModifier);
    }
}
