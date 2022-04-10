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
import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.*;

public class StarWarsApp extends GameApplication {
    private Entity player;
    private Entity enemy;
    private Entity fCard;
    private Entity gCard;
    private Entity hCard;
    private Entity jCard;
    private ArrayList<Card> hand;
    private Deck deck;
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

    public void displayHand() {
        hand = player.getComponent(Deck.class).getHand();
        switch (player.getComponent(Deck.class).checkType(hand.get(0))) {
            case ATTACK -> {
                fCard = spawn("fAtk");
            }
            case DEFENSE -> {
                fCard = spawn("fDef");
            }
            case ATTACKMODIFIER -> {
                fCard = spawn("fAtkBuff");
            }
            case DEFENSEMODIFER -> {
                fCard = spawn("fDefBuff");
            }
            default -> { }
        }

        switch (player.getComponent(Deck.class).checkType(hand.get(1))) {
            case ATTACK -> {
                gCard = spawn("gAtk");
            }
            case DEFENSE -> {
                gCard = spawn("gDef");
            }
            case ATTACKMODIFIER -> {
                gCard = spawn("gAtkBuff");
            }
            case DEFENSEMODIFER -> {
                gCard = spawn("gDefBuff");
            }
            default -> { }
        }

        switch (player.getComponent(Deck.class).checkType(hand.get(2))) {
            case ATTACK -> {
                hCard = spawn("hAtk");
            }
            case DEFENSE -> {
                hCard = spawn("hDef");
            }
            case ATTACKMODIFIER -> {
                hCard = spawn("hAtkBuff");
            }
            case DEFENSEMODIFER -> {
                hCard = spawn("hDefBuff");
            }
            default -> { }
        }

        switch (player.getComponent(Deck.class).checkType(hand.get(3))) {
            case ATTACK -> {
                jCard = spawn("jAtk");
            }
            case DEFENSE -> {
                jCard = spawn("jDef");
            }
            case ATTACKMODIFIER -> {
                jCard = spawn("jAtkBuff");
            }
            case DEFENSEMODIFER -> {
                jCard = spawn("jDefBuff");
            }
            default -> { }
        }
    }
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameElementFactory());

        spawn("background");
        spawn("cardPanel");
        player = spawn("player");
        player.getComponent(Deck.class).drawCard();
        deck = player.getComponent(Deck.class);
        displayHand();


        map = new Map();
        if (!map.getMap().isEmpty()) {
            enemy = spawn("enemy");
            enemy.addComponent(map.getFirstRoom().getEnemy());
        }



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

    public void checkKey(Entity card) {
        if (card.getType() == CardType.ATTACK) {
            player.getComponent(Deck.class).getCard(CardType.ATTACK).attack(player, enemy);
            player.getComponent(PlayerAnimationComponent.class).attackAnimation();
            player.getComponent(Deck.class).usedCard(CardType.ATTACK);
        } else if (card.getType() == CardType.DEFENSE) {
            player.getComponent(Deck.class).getCard(CardType.DEFENSE).defense(player);
            player.getComponent(PlayerAnimationComponent.class).defenseAnimation();
            player.getComponent(Deck.class).usedCard(CardType.DEFENSE);
        } else if (card.getType() == CardType.ATTACKMODIFIER) {
            player.getComponent(Deck.class).getCard(CardType.ATTACKMODIFIER).increaseAttack(player);
            player.getComponent(PlayerAnimationComponent.class).buffAnimation();
            player.getComponent(Deck.class).usedCard(CardType.ATTACKMODIFIER);
        } else if (card.getType() == CardType.DEFENSEMODIFER) {
            player.getComponent(Deck.class).getCard(CardType.DEFENSEMODIFER).increaseAttack(player);
            player.getComponent(PlayerAnimationComponent.class).buffAnimation();
            player.getComponent(Deck.class).usedCard(CardType.DEFENSEMODIFER);
        }
    }

    public void checkHand() {
        if (deck.checkEmptyHand()) {
            deck.drawCard();
            displayHand();
        }
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> {
            if (fCard != null) {
                checkKey(fCard);
                despawnWithScale(fCard);
                fCard = null;
            } else {
                getNotificationService()
                        .pushNotification("No remaining F card.");
            }
            checkHand();
        });
        onKeyDown(KeyCode.G, () -> {
            if (gCard != null) {
                checkKey(gCard);
                despawnWithScale(gCard);
                gCard = null;
            } else {
                getNotificationService()
                        .pushNotification("No remaining G card.");
            }
            checkHand();
        });
        onKeyDown(KeyCode.H, () -> {
            if (hCard != null) {
                checkKey(hCard);
                despawnWithScale(hCard);
                hCard = null;
            } else {
                getNotificationService()
                        .pushNotification("No remaining H card.");
            }
            checkHand();
        });
        onKeyDown(KeyCode.J, () -> {
            if (jCard != null) {
                checkKey(jCard);
                despawnWithScale(jCard);
                jCard = null;
            } else {
                getNotificationService()
                        .pushNotification("No remaining J card.");
            }
            checkHand();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
