package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class GameElementFactory implements EntityFactory {
    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        String texturePath;
        String enemyName = StarWarsApp.getEnemyName();
        if (enemyName.equals("Dooku") || enemyName.equals("Grievous") || enemyName.equals("Windu")) {
            texturePath = "background/" + enemyName + ".png";
        } else {
            Random random = new Random();
            int bgNumber = random.nextInt(1, 7);
            texturePath = "background/" + bgNumber + ".png";
        }
        return FXGL.entityBuilder()
                .view(texturePath)
                .at(0, 0)
                .zIndex(-1)
                .build();
    }

    @Spawns("GamePathMap")
    public Entity newGamePathMap(SpawnData data) {
        GameMap map = FXGL.getWorldProperties().getObject("map");
        StackPane mapStack = new StackPane();
        StackPane roomBoxStack = new StackPane();
        int xTranslateFactor = 0;

        Rectangle bg = new Rectangle(800, 600, Color.BLACK);
        bg.setTranslateZ(-1);
        mapStack.getChildren().add(bg);

        for (int i = 0; i < 3; i++) {
            String enemy = map.getMap().get(i).getEnemy().getName();
            Rectangle roomBox = new Rectangle(150, 60, Color.WHITE);
            roomBox.setTranslateX(xTranslateFactor);
            Text roomText = FXGL.getUIFactoryService().newText(enemy, Color.BLACK, 20);
            roomText.setTranslateX(xTranslateFactor);
            xTranslateFactor += 180;
            roomBoxStack.getChildren().addAll(roomBox, roomText);
        }

        String boss = map.getMap().get(3).getEnemy().getName();
        Rectangle roomBox = new Rectangle(150, 60, Color.WHITE);
        roomBox.setTranslateX(xTranslateFactor);
        Text roomText = FXGL.getUIFactoryService().newText(boss, Color.BLACK, 20);
        roomText.setTranslateX(xTranslateFactor);
        roomBoxStack.getChildren().addAll(roomBox, roomText);
        roomBoxStack.setTranslateX(-400 + 75 + 55);
        mapStack.getChildren().addAll(roomBoxStack);

        Text continueText = FXGL.getUIFactoryService().newText("Press F to Continue.", Color.WHITE, 25);
        continueText.setTranslateY(60);
        mapStack.getChildren().addAll(continueText);

        return FXGL.entityBuilder()
                .view(mapStack)
                .at(0, 0)
                .zIndex(2)
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

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        int userHP = StarWarsApp.getUserHP();
        if (userHP == 0) {
            userHP = 100;
        }
        return FXGL.entityBuilder()
                .type(CharacterType.PLAYER)
                .at(-100, 170)
                .with(new PlayerAnimationComponent())
                .with(new PlayerStats("Anakin", userHP, 0, 0, 0))
                .with(new Deck())
                .build();
    }

    @Spawns("Trooper")
    public Entity newTrooper(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.ENEMY)
                .at(500, 205)
                .with(new EnemyAnimation("Trooper"))
                .build();
    }

    @Spawns("Droid")
    public Entity newDroid(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.ENEMY)
                .at(500, 170)
                .with(new EnemyAnimation("Dooku"))
                .build();
    }

    @Spawns("Guard")
    public Entity newGuard(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.ENEMY)
                .at(500, 170)
                .with(new EnemyAnimation("Dooku"))
                .build();
    }

    @Spawns("Dooku")
    public Entity newDooku(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.BOSS)
                .at(500, 170)
                .with(new EnemyAnimation("Dooku"))
                .build();
    }

    @Spawns("Grievous")
    public Entity newGrievous(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.BOSS)
                .at(500, 170)
                .with(new EnemyAnimation("Dooku"))
                .build();
    }

    @Spawns("Windu")
    public Entity newWindu(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.BOSS)
                .at(500, 170)
                .with(new EnemyAnimation("Dooku"))
                .build();
    }

    @Spawns("fAtk")
    public Entity fAtk(SpawnData data) {
        Texture cardView = FXGL.getAssetLoader().loadTexture("attack.png");
        Text cardText = FXGL.getUIFactoryService().newText("ATTACK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("BLOCK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE ATK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE DEF", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("ATTACK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("BLOCK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE ATK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE DEF", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("ATTACK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("BLOCK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE ATK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE DEF", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("ATTACK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("BLOCK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE ATK", Color.WHITE, 19);
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
        Text cardText = FXGL.getUIFactoryService().newText("INCREASE DEF", Color.WHITE, 19);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(cardView, cardText);
        return FXGL.entityBuilder()
                .view(cardStack)
                .type(CardType.DEFENSEMODIFER)
                .at(630, 410)
                .build();
    }
}
