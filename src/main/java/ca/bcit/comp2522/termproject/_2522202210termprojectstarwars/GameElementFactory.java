package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.core.View;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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

    @Spawns("fAtk")
    public Entity fAtk(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("attack.png");
        Text cardText = FXGL.getUIFactoryService().newText("ATTACK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.ATTACK)
                .with()
                .at(30, 410)
                .build();
    }
    @Spawns("fDef")
    public Entity fDef(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("block.png");
        Text cardText = FXGL.getUIFactoryService().newText("BLOCK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSE)
                .at(30, 410)
                .build();
    }
    @Spawns("fAtkBuff")
    public Entity fAtkBuff(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("atkBuff.png");
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE ATK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.ATTACKMODIFIER)
                .at(30, 410)
                .build();
    }
    @Spawns("fDefBuff")
    public Entity fDefBuff(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("defBuff.png");
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE DEF", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSEMODIFER)
                .at(30, 410)
                .build();
    }






    @Spawns("gAtk")
    public Entity gAtk(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("attack.png");
        Text cardText = FXGL.getUIFactoryService().newText("ATTACK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.ATTACK)
                .at(230, 410)
                .build();
    }
    @Spawns("gDef")
    public Entity gDef(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("block.png");
        Text cardText = FXGL.getUIFactoryService().newText("BLOCK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSE)
                .at(230, 410)
                .build();
    }
    @Spawns("gAtkBuff")
    public Entity gAtkBuff(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("atkBuff.png");
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE ATK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.ATTACKMODIFIER)
                .at(230, 410)
                .build();
    }
    @Spawns("gDefBuff")
    public Entity gDefBuff(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("defBuff.png");
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE DEF", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSEMODIFER)
                .at(230, 410)
                .build();
    }


    @Spawns("hAtk")
    public Entity hAtk(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("attack.png");
        Text cardText = FXGL.getUIFactoryService().newText("ATTACK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.ATTACK)
                .at(430, 410)
                .build();
    }
    @Spawns("hDef")
    public Entity hDef(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("block.png");
        Text cardText = FXGL.getUIFactoryService().newText("BLOCK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSE)
                .at(430, 410)
                .build();
    }
    @Spawns("hAtkBuff")
    public Entity hAtkBuff(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("atkBuff.png");
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE ATK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.ATTACKMODIFIER)
                .at(430, 410)
                .build();
    }
    @Spawns("hDefBuff")
    public Entity hDefBuff(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("defBuff.png");
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE DEF", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSEMODIFER)
                .at(430, 410)
                .build();
    }




    @Spawns("jAtk")
    public Entity jAtk(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("attack.png");
        Text cardText = FXGL.getUIFactoryService().newText("ATTACK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.ATTACK)
                .at(630, 410)
                .build();
    }
    @Spawns("jDef")
    public Entity jDef(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("block.png");
        Text cardText = FXGL.getUIFactoryService().newText("BLOCK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSE)
                .at(630, 410)
                .build();
    }
    @Spawns("jAtkBuff")
    public Entity jAtkBuff(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("atkBuff.png");
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE ATK", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.ATTACKMODIFIER)
                .at(630, 410)
                .build();
    }
    @Spawns("jDefBuff")
    public Entity jDefBuff(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("defBuff.png");
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE DEF", Color.WHITE, 20);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSEMODIFER)
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
                .with(new Deck())
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.ENEMY)
                .at(500, 170)
                .with(new EnemyAnimationComponent())
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
