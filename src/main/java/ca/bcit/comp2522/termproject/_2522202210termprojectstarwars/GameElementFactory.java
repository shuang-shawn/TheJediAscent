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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameElementFactory implements EntityFactory {
    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder()
                .view("background/Flagship.png")
                .at(0, 0)
                .zIndex(-1)
                .with(new IrremovableComponent())
                .build();
    }

    @Spawns("cardPanel")
    public Entity newCardPanel(SpawnData data) {
        return FXGL.entityBuilder()
                .view(new Rectangle(800, 300, Color.BLACK))
                .at(0, 330)
                .zIndex(-1)
                .with(new IrremovableComponent())
                .build();
    }

    @Spawns("fCard")
    public Entity fCard(SpawnData data) {
        return FXGL.entityBuilder()
                .view(new Rectangle(140, 180, Color.WHITE))
                .at(30, 410)
                .build();
    }

    @Spawns("gCard")
    public Entity gCard(SpawnData data) {
        return FXGL.entityBuilder()
                .view(new Rectangle(140, 180, Color.WHITE))
                .at(230, 410)
                .build();
    }

    @Spawns("hCard")
    public Entity hCard(SpawnData data) {
        return FXGL.entityBuilder()
                .view(new Rectangle(140, 180, Color.WHITE))
                .at(430, 410)
                .build();
    }

    @Spawns("jCard")
    public Entity jCard(SpawnData data) {
        return FXGL.entityBuilder()
                .view(new Rectangle(140, 180, Color.WHITE))
                .at(630, 410)
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

    @Spawns("test")
    public Entity test(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.ENEMY)
                .at(500, 170)
                .with(new EnemyAnimationComponent())
                .with(new EnemyStats("Dooku", 20, 0, 0, 0))
                .build();
    }
}
