package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class PlayerAnimationComponent extends AnimationComponent {
    private static final String IDLE_ASSET = "AnakinIdle.png";
    private static final String RUN_ASSET = "AnakinRun.png";
    private static final String ATTACK_ASSET = "AnakinAttack.png";
    private final AnimationChannel animRun;
    private final AnimationChannel animIdle;
    private final AnimationChannel animAttack;

    public PlayerAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image(IDLE_ASSET),
                5, 94, 132, Duration.seconds(1), 0, 4);
        animRun = new AnimationChannel(FXGL.image(RUN_ASSET),
                13, 124, 130, Duration.seconds(1.5), 0, 12);
        animAttack = new AnimationChannel(FXGL.image(RUN_ASSET),
                15, 172, 136, Duration.seconds(1.5), 0, 14);
        texture = new AnimatedTexture(animIdle);
        texture.playAnimationChannel(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {
        if (entity.getX() < 200) {
            entity.translateX(speed * tpf);
            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animRun);
            }
            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
            }
        } else {
            if (frameCounter > 0) {
                frameCounter--;
            }
            if (texture.getAnimationChannel() != animIdle && frameCounter == 0) {
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    public void attackAnimation() {
        frameCounter = 100;
        texture.loopAnimationChannel(animAttack);
    }

    public void defenseAnimation() {
        frameCounter = 100;
        texture.loopAnimationChannel(animRun);
    }

    public void buffAnimation() {
        frameCounter = 100;
        texture.loopAnimationChannel(animRun);
    }
}
