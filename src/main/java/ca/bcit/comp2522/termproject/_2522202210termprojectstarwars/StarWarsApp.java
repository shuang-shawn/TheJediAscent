package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


import static com.almasb.fxgl.dsl.FXGL.*;

public class StarWarsApp extends GameApplication {
    private Entity player;
    private Entity enemy;
    private Card attackCard;
    private Card defenseCard;
    private Card attackModifierCard;
    private IntegerProperty playerHP;
    private IntegerProperty enemyHP;
    private IntegerProperty playerDefense;
    private IntegerProperty playerAttackModifier;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameElementFactory());

        spawn("background");
        spawn("cardPanel");
        player = FXGL.spawn("player");
        enemy = FXGL.spawn("enemy");

        this.attackCard = new AttackCard(10);
        this.defenseCard = new DefenseCard(5);
        this.attackModifierCard = new AttackModifierCard(1);

        this.playerHP = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getHp());
        this.enemyHP = new SimpleIntegerProperty(enemy.getComponent(EnemyStats.class).getHp());
        this.playerDefense = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getDefense());
        this.playerAttackModifier = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getAttackModifier());
    }

    @Override
    protected void initUI() {
        int rowOneY = 345;
        int rowHeight = 20;
        int playerTextX = 190;
        int enemyTextX = 500;
        int textSize = 17;

        Text playerHP = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        playerHP.setTranslateX(playerTextX);
        playerHP.setTranslateY(rowOneY);
        playerHP.textProperty().bind(this.playerHP.asString("Player HP: [%d]"));
        getGameScene().addUINode(playerHP);

        Text enemyHP = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        enemyHP.setTranslateX(enemyTextX);
        enemyHP.setTranslateY(rowOneY);
        enemyHP.textProperty().bind(this.enemyHP.asString("Enemy HP: [%d]"));
        getGameScene().addUINode(enemyHP);

        Text playerDefense = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        playerDefense.setTranslateX(playerTextX);
        playerDefense.setTranslateY(rowOneY+rowHeight);
        playerDefense.textProperty().bind(this.playerDefense.asString("Player defense: [%d]"));
        getGameScene().addUINode(playerDefense);

        Text playerAttackModifier = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        playerAttackModifier.setTranslateX(playerTextX);
        playerAttackModifier.setTranslateY(rowOneY+rowHeight*2);
        playerAttackModifier.textProperty().bind(this.playerAttackModifier.asString("Player attack modifier: [%d]"));
        getGameScene().addUINode(playerAttackModifier);
    }

    @Override
    protected void onUpdate(double tpf) {
        playerHP.set(player.getComponent(PlayerStats.class).getHp());
        enemyHP.set(enemy.getComponent(EnemyStats.class).getHp());
        playerDefense.set(player.getComponent(PlayerStats.class).getDefense());
        playerAttackModifier.set(player.getComponent(PlayerStats.class).getAttackModifier());
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> {
            attackCard.attack(player, enemy);
            player.getComponent(PlayerAnimationComponent.class).attackAnimation();
            System.out.println(player.getType());
        });
        onKeyDown(KeyCode.G, () -> {
            defenseCard.defense(player);
            player.getComponent(PlayerAnimationComponent.class).defenseAnimation();
        });
        onKeyDown(KeyCode.H, () -> {
            attackModifierCard.increaseAttack(player);
            player.getComponent(PlayerAnimationComponent.class).buffAnimation();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
