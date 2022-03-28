package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public class CharacterFactory implements EntityFactory {
    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.PLAYER)
                .at(-100, 200)
                .with(new PlayerAnimationComponent())
                .with(new PlayerStats(20, 0, 0, 0))
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder()
                .type(CharacterType.ENEMY)
                .at(500, 200)
                .with(new EnemyAnimationComponent())
                .with(new EnemyStats(20, 0, 0, 0))
                .build();
    }
}
