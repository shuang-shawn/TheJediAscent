package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class GameElementFactory implements EntityFactory {
    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder()
                .view("background/AnakinAndObiwanVsDooku.png")
                .zIndex(-1)
                .with(new IrremovableComponent())
                .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.PLAYER)
                .at(-100, 170)
                .with(new PlayerAnimationComponent())
                .with(new PlayerStats("Anakin", 20, 0, 0, 0))
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.ENEMY)
                .at(500, 170)
                .with(new EnemyAnimationComponent())
                .with(new EnemyStats("Dooku", 20, 0, 0, 0))
                .build();
    }
}
