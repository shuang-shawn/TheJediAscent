package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.collection.PropertyMap;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;

public class StarWarsApp extends GameApplication {
    private static String userName;
    private static String enemyName;
    private static int userHp = 0;
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
    private GameMap map;
    private PropertyMap state;
    private Entity gameMapUI;
    private Entity background;

    @Override
    protected void onPreInit() {
        try {
            DatabaseOperation.connectDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        state = FXGL.getWorldProperties();
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("hp", 100);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Star Wars");
        settings.setSceneFactory(new MySceneFactory());
        settings.setGameMenuEnabled(true);
        settings.setMainMenuEnabled(true);
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new GameElementFactory());

        map = new GameMap();
        enemyName = map.getFirstRoom().getEnemy().getName();

        enemy = spawn(enemyName);
        enemy.addComponent(map.getFirstRoom().getEnemy());
        enemyStats = enemy.getComponent(EnemyStats.class);
        enemyAction = new EnemyAction(enemy);

        FXGL.getWorldProperties().setValue("map", map);

        gameMapUI = spawn("GamePathMap");
        gameMapUI.setVisible(false);
        background= spawn("background");
        spawn("cardPanel");
        player = spawn("player");
        player.getComponent(Deck.class).drawCard();
        deck = player.getComponent(Deck.class);
        displayHand();

        this.playerHP = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getHp());
        this.enemyHP = new SimpleIntegerProperty(enemy.getComponent(EnemyStats.class).getHp());
        this.playerDefense = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getDefense());
        this.enemyDefense = new SimpleIntegerProperty(enemy.getComponent(EnemyStats.class).getDefense());
        this.playerAttackModifier = new SimpleIntegerProperty(player.getComponent(PlayerStats.class).getAttackModifier());
        this.enemyAttackModifier = new SimpleIntegerProperty(enemy.getComponent(EnemyStats.class).getAttackModifier());
    }

    @Override
    protected void initUI() {
        addUI();
    }

    private void addUI() {
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
        playerDefense.setTranslateY(rowOneY + rowHeight);
        playerDefense.textProperty().bind(this.playerDefense.asString("Player defense: [%d]"));
        getGameScene().addUINode(playerDefense);

        Text enemyDefense = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        enemyDefense.setTranslateX(enemyTextX);
        enemyDefense.setTranslateY(rowOneY + rowHeight);
        enemyDefense.textProperty().bind(this.enemyDefense.asString("Enemy defense: [%d]"));
        getGameScene().addUINode(enemyDefense);

        Text playerAttackModifier = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        playerAttackModifier.setTranslateX(playerTextX);
        playerAttackModifier.setTranslateY(rowOneY + rowHeight * 2);
        playerAttackModifier.textProperty().bind(this.playerAttackModifier.asString("Player attack modifier: [%d]"));
        getGameScene().addUINode(playerAttackModifier);

        Text enemyAttackModifier = FXGL.getUIFactoryService().newText("", Color.WHITE, textSize);
        enemyAttackModifier.setTranslateX(enemyTextX);
        enemyAttackModifier.setTranslateY(rowOneY + rowHeight * 2);
        enemyAttackModifier.textProperty().bind(this.enemyAttackModifier.asString("Enemy attack modifier: [%d]"));
        getGameScene().addUINode(enemyAttackModifier);
    }

    @Override
    protected void onUpdate(double tpf) {
        if (player.getComponent(PlayerStats.class).getHp() > 0) {
            playerHP.set(player.getComponent(PlayerStats.class).getHp());
            playerDefense.set(player.getComponent(PlayerStats.class).getDefense());
            playerAttackModifier.set(player.getComponent(PlayerStats.class).getAttackModifier());
        }
        if (enemy != null) {
            enemyHP.set(enemy.getComponent(EnemyStats.class).getHp());
            enemyDefense.set(enemy.getComponent(EnemyStats.class).getDefense());
            enemyAttackModifier.set(enemy.getComponent(EnemyStats.class).getAttackModifier());
        } else {
            enemyHP.set(0);
            enemyDefense.set(0);
            enemyAttackModifier.set(0);
        }
    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.F, () -> {
            if (gameMapUI.isVisible()) {
                gameMapUI.setVisible(false);
                addUI();
                fillHand();
            } else if (fCard != null) {
                playCard(fCard);
                despawnWithScale(fCard);
                fCard = null;
                checkHand();
                checkEnemyDead();
                enemyAction.execute(player);
            } else {
                getNotificationService()
                        .pushNotification("No remaining F card.");
            }
        });
        onKeyDown(KeyCode.G, () -> {
            if (gCard != null && !gameMapUI.isVisible()) {
                playCard(gCard);
                despawnWithScale(gCard);
                gCard = null;
                checkHand();
                checkEnemyDead();
                enemyAction.execute(player);
            } else {
                getNotificationService()
                        .pushNotification("No remaining G card.");
            }
        });
        onKeyDown(KeyCode.H, () -> {
            if (hCard != null && !gameMapUI.isVisible()) {
                playCard(hCard);
                despawnWithScale(hCard);
                hCard = null;
                checkHand();
                checkEnemyDead();
                enemyAction.execute(player);
            } else {
                getNotificationService()
                        .pushNotification("No remaining H card.");
            }
        });
        onKeyDown(KeyCode.J, () -> {
            if (jCard != null && !gameMapUI.isVisible()) {
                playCard(jCard);
                despawnWithScale(jCard);
                jCard = null;
                checkHand();
                checkEnemyDead();
                enemyAction.execute(player);
            } else {
                getNotificationService()
                        .pushNotification("No remaining J card.");
            }
        });
        onKeyDown(KeyCode.M, () -> {
            gameMapUI.setVisible(true);
            getGameScene().clearUINodes();
        });
    }

    public static void setUserName(String inputUserName) {
        userName = inputUserName;
    }

    public static void setUserHp(int userSavedHp) {
        userHp = userSavedHp;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getEnemyName() {
        return enemyName;
    }

    public static int getUserHP() {
        return userHp;
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
            default -> {
            }
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
            default -> {
            }
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
            default -> {
            }
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
            default -> {
            }
        }
    }

    public void playCard(Entity card) {
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

    public void fillHand() {
        deck.drawCard();
        displayHand();
    }

    public void checkEnemyDead() {
        if (enemyStats.getHp() <= 0) {
            despawnWithScale(enemy);
            background.removeFromWorld();
            enemy = null;
            enemyStats = null;
            map.clearFirstRoom();
            if (!map.getMap().isEmpty()) {
                gameMapUI.setVisible(true);
                getGameScene().clearUINodes();
                enemyName = map.getFirstRoom().getEnemy().getName();
                enemy = spawn(enemyName);
                background = spawn("background");
                enemy.addComponent(map.getFirstRoom().getEnemy());
                enemyStats = enemy.getComponent(EnemyStats.class);
                enemyAction = new EnemyAction(enemy);

            }
            else{
                FXGL.getGameController().gotoMainMenu();
            }
        }
    }

    public void checkPlayerDead() {
        if (player.getComponent(PlayerStats.class).getHp() <= 0) {
            spawn(map.getFirstRoom().getEnemy().getName());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
