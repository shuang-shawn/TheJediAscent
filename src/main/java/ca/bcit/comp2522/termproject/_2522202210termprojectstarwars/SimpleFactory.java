package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SimpleFactory implements EntityFactory {
    @Spawns("card")
    public Entity newCard(SpawnData data) {
        return FXGL.entityBuilder(data)
                .view(new Rectangle(40, 40, Color.RED))
                .build();
    }
}
