package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class DookuAnimationComponent extends AnimationComponent {
    private static final String IDLE_ASSET = "DookuIdle.png";
    private final AnimationChannel animIdle;

    public DookuAnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image(IDLE_ASSET),
                7, 96, 132, Duration.seconds(1), 0, 6);
        texture = new AnimatedTexture(animIdle);
        texture.loopAnimationChannel(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {

    }
}
