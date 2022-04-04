package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.event.EventBus;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.Scene;
import com.almasb.fxgl.time.TimerAction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;


import java.sql.SQLException;

import static com.almasb.fxgl.dsl.FXGL.*;

public class StarWarsApp extends GameApplication {
    private Entity player;
    private Entity enemy;
    private Entity fCard;
    private Entity gCard;
    private Entity hCard;
    private Entity jCard;
    private Card attackCard;
    private Card defenseCard;
    private Card attackModifierCard;
    private IntegerProperty playerHP;
    private IntegerProperty enemyHP;
    private IntegerProperty playerDefense;
    private IntegerProperty playerAttackModifier;

    @Override
    protected void onPreInit() {
        try {
            DatabaseOperation.readData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Star Wars");
        settings.setSceneFactory(new MySceneFactory());
        settings.setGameMenuEnabled(true);
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameElementFactory());

        spawn("background");
        spawn("cardPanel");

        fCard = spawn("fCard");
        gCard = spawn("gCard");
        hCard = spawn("hCard");
        jCard = spawn("jCard");

        player = spawn("player");
        enemy = spawn("enemy");

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
        int rowOneY = 350;
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
        });
        onKeyDown(KeyCode.G, () -> {
            defenseCard.defense(player);
            player.getComponent(PlayerAnimationComponent.class).defenseAnimation();
        });
        onKeyDown(KeyCode.H, () -> {
            attackModifierCard.increaseAttack(player);
            player.getComponent(PlayerAnimationComponent.class).buffAnimation();
        });
        onKeyDown(KeyCode.J, () -> {
            attackCard.attack(player, enemy);
            player.getComponent(PlayerAnimationComponent.class).attackAnimation();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
