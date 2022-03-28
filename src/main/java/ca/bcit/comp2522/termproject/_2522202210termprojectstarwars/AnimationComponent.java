package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class AnimationComponent extends Component {

    protected int speed = 100;
    protected AnimatedTexture texture;

    public AnimationComponent() {
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }
}
