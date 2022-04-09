package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class MySceneFactory extends SceneFactory {

    @Override
    public FXGLMenu newMainMenu() {
        return new MyMenu(MenuType.MAIN_MENU
        );
    }

    @Override
    public FXGLMenu newGameMenu() {
        return new MyMenu(MenuType.GAME_MENU);
    }
}
