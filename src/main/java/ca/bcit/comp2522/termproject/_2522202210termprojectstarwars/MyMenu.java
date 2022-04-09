package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.View;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.FontType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MyMenu extends FXGLMenu {

    private static final int SIZE = 150;
    private Animation<?> animation;

    public MyMenu(MenuType type) {
        super(type);

        if (type == MenuType.GAME_MENU) {
            MyGameMenu();
        }
        if (type == MenuType.MAIN_MENU) {
            MyMainMenu();
        }
    }

    private void MyGameMenu() {
        getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0);

        Rectangle bg = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight(), Color.BLACK);
        bg.setTranslateY(FXGL.getAppHeight() / 2.0 * -1);

        Text textResume = FXGL.getUIFactoryService().newText("RESUME", Color.WHITE, 20);
        Rectangle resumeBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane resumeStack = new StackPane();
        resumeStack.getChildren().addAll(resumeBox, textResume);
        resumeStack.setOnMouseClicked(e -> fireResume());

        Text textSave = FXGL.getUIFactoryService().newText("Save", Color.WHITE, 20);
        Rectangle saveBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane saveStack = new StackPane();
        saveStack.getChildren().addAll(saveBox, textSave);
        saveStack.setOnMouseClicked(e -> DatabaseOperation.writeData());
        saveStack.setTranslateY(30);

        Text textExit = FXGL.getUIFactoryService().newText("Exit", Color.WHITE, 20);
        Rectangle exitBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane exitStack = new StackPane();
        exitStack.getChildren().addAll(exitBox, textExit);
        exitStack.setOnMouseClicked(e -> FXGL.getGameController().exit());
        exitStack.setTranslateY(60);

        getContentRoot().getChildren().addAll(bg, resumeStack, saveStack, exitStack);
    }

    private void MyMainMenu() {
        getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0);

        Rectangle bg = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight(), Color.BLACK);
        bg.setTranslateY(FXGL.getAppHeight() / 2.0 * -1);

        Text testStartGame = FXGL.getUIFactoryService().newText("Start Game", Color.WHITE, 20);
        Rectangle startGameBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane startGameStack = new StackPane();
        startGameStack.getChildren().addAll(startGameBox, testStartGame);
        startGameStack.setOnMouseClicked(e -> fireNewGame());
        startGameStack.setTranslateY(50);

        Text testLoad = FXGL.getUIFactoryService().newText("Load", Color.WHITE, 20);
        Rectangle loadBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane loadStack = new StackPane();
        loadStack.getChildren().addAll(loadBox, testLoad);
//        loadStack.setOnMouseClicked(e -> DatabaseOperation.writeData());
        loadStack.setTranslateY(80);

        Text textExit = FXGL.getUIFactoryService().newText("Exit", Color.WHITE, 20);
        Rectangle exitBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane exitStack = new StackPane();
        exitStack.getChildren().addAll(exitBox, textExit);
        exitStack.setOnMouseClicked(e -> FXGL.getGameController().exit());
        exitStack.setTranslateY(110);

        getContentRoot().getChildren().addAll(bg, startGameStack, loadStack, exitStack);
    }
}
