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
    private Card defenseModifierCard;
    private IntegerProperty playerHP;
    private IntegerProperty enemyHP;
    private IntegerProperty playerDefense;
    private IntegerProperty enemyDefense;
    private IntegerProperty playerAttackModifier;
    private IntegerProperty enemyAttackModifier;
    private EnemyStats enemyStats;
    private EnemyAction enemyAction;
    private Map map;

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



        map = new Map();
        if (!map.getMap().isEmpty()) {
            enemy = spawn("enemy");
            enemy.addComponent(map.getFirstRoom().getEnemy());
        }

        this.attackCard = new AttackCard(10);
        this.defenseCard = new DefenseCard(5);
        this.attackModifierCard = new AttackModifierCard(1);
        this.defenseModifierCard = new DefenseModifierCard(1);

        enemyAction = new EnemyAction(enemy);

        this.playerHP = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getHp());
        this.enemyHP = new SimpleIntegerProperty(enemy.getComponent(EnemyStats.class).getHp());
        this.playerDefense = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getDefense());
        this.enemyDefense = new SimpleIntegerProperty(enemy.getComponent(EnemyStats.class).getDefense());
        this.playerAttackModifier = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getAttackModifier());
        this.enemyAttackModifier = new SimpleIntegerProperty(enemy.getComponent(EnemyStats.class).getAttackModifier());
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

        Text enemyDefense = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        enemyDefense.setTranslateX(enemyTextX);
        enemyDefense.setTranslateY(rowOneY+rowHeight);
        enemyDefense.textProperty().bind(this.enemyDefense.asString("Enemy defense: [%d]"));
        getGameScene().addUINode(enemyDefense);

        Text playerAttackModifier = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        playerAttackModifier.setTranslateX(playerTextX);
        playerAttackModifier.setTranslateY(rowOneY+rowHeight*2);
        playerAttackModifier.textProperty().bind(this.playerAttackModifier.asString("Player attack modifier: [%d]"));
        getGameScene().addUINode(playerAttackModifier);

        Text enemyAttackModifier = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        enemyAttackModifier.setTranslateX(enemyTextX);
        enemyAttackModifier.setTranslateY(rowOneY+rowHeight*2);
        enemyAttackModifier.textProperty().bind(this.enemyAttackModifier.asString("Enemy attack modifier: [%d]"));
        getGameScene().addUINode(enemyAttackModifier);
    }

    @Override
    protected void onUpdate(double tpf) {
        playerHP.set(player.getComponent(PlayerStats.class).getHp());
        enemyHP.set(enemy.getComponent(EnemyStats.class).getHp());
        playerDefense.set(player.getComponent(PlayerStats.class).getDefense());
        enemyDefense.set(enemy.getComponent(EnemyStats.class).getDefense());
        playerAttackModifier.set(player.getComponent(PlayerStats.class).getAttackModifier());
        enemyAttackModifier.set(enemy.getComponent(EnemyStats.class).getAttackModifier());
        if (player.getComponent(Deck.class).getDeck().isEmpty()) {
            player.getComponent(Deck.class).refreshDeck();
            fCard = spawn("fCard");
            gCard = spawn("gCard");
            hCard = spawn("hCard");
            jCard = spawn("jCard");
        }


    }

//    protected void checkDead() {
//        if (enemy.getComponent(EnemyStats.class).getHp() <= 0) {
//            despawnWithScale(enemy);
//            player.getComponent(Deck.class).addCard(map.getFirstRoom().getRewardCard());
//            if (map.getMap().iterator().hasNext()) {
//                map.clearFirstRoom();
//                enemy = spawn("enemy");
//                enemy.addComponent(map.getFirstRoom().getEnemy());
//                enemyAction = new EnemyAction(enemy);
//            } else {
//                map.clearFirstRoom();
//            }
//        }
//        if (map.getMap().isEmpty()) {
//            getNotificationService()
//                    .pushNotification("You have pass the trial.");
//        }
//    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> {
            if (player.getComponent(Deck.class).checkCard(CardType.ATTACK)) {
                player.getComponent(Deck.class).getCard(CardType.ATTACK).attack(player, enemy);
                player.getComponent(PlayerAnimationComponent.class).attackAnimation();
                player.getComponent(Deck.class).usedCard(CardType.ATTACK);
                despawnWithScale(fCard);
//                if (player.getComponent(Deck.class).checkCard(CardType.ATTACK)) {
//                    despawnWithScale(fCard);
//                }
                enemyAction.execute(player);
//                checkDead();
            } else {
                getNotificationService()
                        .pushNotification("No remaining attack card.");
            }

        });
        onKeyDown(KeyCode.G, () -> {
            if (player.getComponent(Deck.class).checkCard(CardType.DEFENSE)) {
                player.getComponent(Deck.class).getCard(CardType.DEFENSE).defense(player);
                player.getComponent(PlayerAnimationComponent.class).defenseAnimation();
                despawnWithScale(gCard);
                player.getComponent(Deck.class).usedCard(CardType.DEFENSE);
//                if (player.getComponent(Deck.class).checkCard(CardType.DEFENSE)) {
//                    despawnWithScale(gCard);
//                }
                enemyAction.execute(player);
//                checkDead();
            } else {
                getNotificationService()
                        .pushNotification("No remaining defense card.");
            }
        });
        onKeyDown(KeyCode.H, () -> {
            if (player.getComponent(Deck.class).checkCard(CardType.ATTACKMODIFIER)) {
                player.getComponent(Deck.class).getCard(CardType.ATTACKMODIFIER).increaseAttack(player);
                player.getComponent(PlayerAnimationComponent.class).buffAnimation();
                despawnWithScale(hCard);
                player.getComponent(Deck.class).usedCard(CardType.ATTACKMODIFIER);
//                if (player.getComponent(Deck.class).checkCard(CardType.ATTACKMODIFIER)) {
//                    despawnWithScale(hCard);
//                }
                enemyAction.execute(player);
//                checkDead();
            } else {
                getNotificationService()
                        .pushNotification("No remaining attack modifier card.");
            }
        });
        onKeyDown(KeyCode.J, () -> {
            if (player.getComponent(Deck.class).checkCard(CardType.DEFENSEMODIFER)) {
                player.getComponent(Deck.class).getCard(CardType.DEFENSEMODIFER).increaseDefense(player);
                player.getComponent(PlayerAnimationComponent.class).buffAnimation();
                despawnWithScale(jCard);
                player.getComponent(Deck.class).usedCard(CardType.DEFENSEMODIFER);
//                if (player.getComponent(Deck.class).checkCard(CardType.DEFENSEMODIFER)) {
//                    despawnWithScale(jCard);
//                }
                enemyAction.execute(player);
//                checkDead();
            } else {
                getNotificationService()
                        .pushNotification("No remaining defense modifier card.");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
