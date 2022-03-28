package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;


public class AnimationComponent extends Component {

    protected double speed = 150;
    protected AnimatedTexture texture;
    protected int frameCounter = 0;

    public AnimationComponent() {
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }
}
