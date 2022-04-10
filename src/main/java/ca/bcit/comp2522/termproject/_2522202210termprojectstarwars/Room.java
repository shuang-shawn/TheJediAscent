package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import java.util.concurrent.ThreadLocalRandom;

public class Room extends Entity {
    private EnemyStats enemy;
    private Card rewardCard;
    private RoomType type;

    public Room() {
        type = RoomType.NORMAL;
        int name = ThreadLocalRandom.current().nextInt(0, 3);
        int hp = ThreadLocalRandom.current().nextInt(20, 50);
        int defense = ThreadLocalRandom.current().nextInt(0, 2);
        int reward = ThreadLocalRandom.current().nextInt(0, 4);
        switch (name) {
            case 0 -> {
                enemy = new EnemyStats("Storm Trooper", hp, defense, 0, 0);
            }
            case 1 -> {
                enemy = new EnemyStats("R2D2", hp, defense, 0, 0);
            }
            case 2 -> {
                enemy = new EnemyStats("Alien", hp, defense, 0, 0);
            }
            default -> { }
        }

        switch (reward) {
            case 0 -> {
                rewardCard = new AttackCard(10);
            }
            case 1 -> {
                rewardCard = new DefenseCard(5);
            }
            case 2 -> {
                rewardCard = new AttackModifierCard(1);
            }
            case 3 -> {
                rewardCard = new DefenseModifierCard(1);
            }
        }

    }

    public Room(int boss) {
        type = RoomType.BOSS;
        int hp = ThreadLocalRandom.current().nextInt(70, 100);
        int defense = ThreadLocalRandom.current().nextInt(3, 4);
        int reward = ThreadLocalRandom.current().nextInt(0, 4);
        switch (boss) {
            case 0 -> {
                enemy = new EnemyStats("Darth Sidious", hp, defense, 0, 0);
            }
            case 1 -> {
                enemy = new EnemyStats("Darth Vader", hp, defense, 0, 0);
            }
            case 2 -> {
                enemy = new EnemyStats("Kylo Ren", hp, defense, 0, 0);
            }
            default -> { }
        }
        switch (reward) {
            case 0 -> {
                rewardCard = new AttackCard(20);
            }
            case 1 -> {
                rewardCard = new DefenseCard(10);
            }
            case 2 -> {
                rewardCard = new AttackModifierCard(2);
            }
            case 3 -> {
                rewardCard = new DefenseModifierCard(2);
            }
            default -> { }
        }
    }

    public EnemyStats getEnemy() {
        return enemy;
    }

    public Card getRewardCard() {
        return rewardCard;
    }

    public RoomType getRoomType() {
        return type;
    }

    @Override
    public String toString() {
        return enemy.getName() +" room";
    }
}
