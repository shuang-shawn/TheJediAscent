package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class PlayerAnimationComponent extends AnimationComponent {
    private static final String IDLE_ASSET = "AnakinIdle1.png";
    private static final String RUN_ASSET = "AnakinRun1.png";
    private final AnimationChannel animWalk;
    private final AnimationChannel animIdle;

    public PlayerAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image(IDLE_ASSET),
                5, 94, 132, Duration.seconds(1), 0, 4);
        animWalk = new AnimationChannel(FXGL.image(RUN_ASSET),
                13, 124, 130, Duration.seconds(1.5), 0, 12);
        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {
        if (entity.getX() < 200) {
            entity.translateX(speed * tpf);
            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animWalk);
            }
            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
            }
        } else {
            if (texture.getAnimationChannel() == animWalk) {
                texture.loopAnimationChannel(animIdle);
            }
        }
    }
}
