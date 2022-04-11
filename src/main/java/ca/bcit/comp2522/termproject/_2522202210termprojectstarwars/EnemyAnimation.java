package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public class EnemyAnimation extends Component {
    private AnimationComponent enemyAnimationComponent;

    public EnemyAnimation(String name){
        if (name.equals("Trooper")){
            enemyAnimationComponent = new TrooperAnimationComponent();
        }
        else if (name.equals("Dooku")){
            enemyAnimationComponent = new DookuAnimationComponent();
        }
    }

    public AnimationComponent getEnemyAnimationComponent() {
        return enemyAnimationComponent;
    }
}
