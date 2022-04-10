package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Optional;

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
        saveStack.setOnMouseClicked(e -> DatabaseOperation.updateData(StarWarsApp.getUserName(),FXGL.getWorldProperties().getInt("hp")));
        saveStack.setTranslateY(30);

        Text textMenu = FXGL.getUIFactoryService().newText("Main Menu", Color.WHITE, 20);
        Rectangle menuBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane menuStack = new StackPane();
        menuStack.getChildren().addAll(menuBox, textMenu);
        menuStack.setOnMouseClicked(e -> FXGL.getGameController().gotoMainMenu());
        menuStack.setTranslateY(60);

        getContentRoot().getChildren().addAll(bg, resumeStack, saveStack, menuStack);
    }

    private void MyMainMenu() {
        getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0);

        Rectangle bg = new Rectangle(FXGL.getAppWidth(), FXGL.getAppHeight(), Color.BLACK);
        bg.setTranslateY(FXGL.getAppHeight() / 2.0 * -1);

        Text testStartNewGame = FXGL.getUIFactoryService().newText("New Game", Color.WHITE, 20);
        Rectangle startNewGameBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane startNewGameStack = new StackPane();
        startNewGameStack.getChildren().addAll(startNewGameBox, testStartNewGame);
        startNewGameStack.setOnMouseClicked(e -> nameInputBox());
        startNewGameStack.setTranslateY(50);

        Text testLoad = FXGL.getUIFactoryService().newText("Load", Color.WHITE, 20);
        Rectangle loadBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane loadStack = new StackPane();
        loadStack.getChildren().addAll(loadBox, testLoad);
        loadStack.setOnMouseClicked(e -> loadSubMenu());
        loadStack.setTranslateY(80);

        Text textExit = FXGL.getUIFactoryService().newText("Exit", Color.WHITE, 20);
        Rectangle exitBox = new Rectangle(FXGL.getAppWidth(), 20, Color.TRANSPARENT);
        StackPane exitStack = new StackPane();
        exitStack.getChildren().addAll(exitBox, textExit);
        exitStack.setOnMouseClicked(e -> FXGL.getGameController().exit());
        exitStack.setTranslateY(110);

        getContentRoot().getChildren().addAll(bg, startNewGameStack, loadStack, exitStack);
    }

    private void nameInputBox(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Game");
        dialog.setHeaderText("Please enter your name.");
        dialog.setContentText("If you input a used name, previous data will be overwritten.");

        dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                Bindings.isEmpty(dialog.getEditor().textProperty())
        );

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            DatabaseOperation.newData(result.get());
            StarWarsApp.setUserName(result.get());
            fireNewGame();
        }
    }

    private void loadSubMenu(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Load Game");
        dialog.setHeaderText("Please enter your name.");
        dialog.setContentText("If no matched data found, you will return to the main menu.");

        dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                Bindings.isEmpty(dialog.getEditor().textProperty())
        );

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String recordFound = DatabaseOperation.readData(result.get());
            if (!(recordFound.equals("User not found.") || recordFound.equals("Error reading database.")) ){
                StarWarsApp.setUserName(result.get());
                StarWarsApp.setUserHp(Integer.parseInt(recordFound));
                fireNewGame();
            }
        }
    }

}
