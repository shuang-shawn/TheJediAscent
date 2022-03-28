package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


import static com.almasb.fxgl.dsl.FXGL.*;

public class StarWarsApp extends GameApplication{
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
    protected void initSettings(GameSettings gameSettings) {
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new CharacterFactory());

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
        Text playerHP = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        playerHP.setTranslateX(20);
        playerHP.setTranslateY(20);
        playerHP.textProperty().bind(this.playerHP.asString("Current Player HP: [%d]"));
        getGameScene().addUINode(playerHP);

        Text enemyHP = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        enemyHP.setTranslateX(450);
        enemyHP.setTranslateY(20);
        enemyHP.textProperty().bind(this.enemyHP.asString("Current EnemyStats HP: [%d]"));
        getGameScene().addUINode(enemyHP);

        Text playerDefense = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        playerDefense.setTranslateX(20);
        playerDefense.setTranslateY(40);
        playerDefense.textProperty().bind(this.playerDefense.asString("Current Player defense: [%d]"));
        getGameScene().addUINode(playerDefense);

        Text playerAttackModifier = FXGL.getUIFactoryService().newText("", Color.BLACK, 24);
        playerAttackModifier.setTranslateX(20);
        playerAttackModifier.setTranslateY(60);
        playerAttackModifier.textProperty().bind(this.playerAttackModifier.asString("Current Player attack modifier: [%d]"));
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
        });
        onKeyDown(KeyCode.G, () -> {
            defenseCard.defense(player);
        });
        onKeyDown(KeyCode.H, () -> {
            attackModifierCard.increaseAttack(player);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
