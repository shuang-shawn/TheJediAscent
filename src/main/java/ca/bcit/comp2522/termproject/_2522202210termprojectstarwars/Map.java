package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Map extends Entity {
    private ArrayList<Room> map = new ArrayList<>();

    public Map() {
        int seed = ThreadLocalRandom.current().nextInt(0, 2);
        Room roomOne = new Room();
        Room roomTwo = new Room();
        Room roomThree = new Room();
        Room bossRoom = new Room(seed);
        map.add(roomOne);
        map.add(roomTwo);
        map.add(roomThree);
        map.add(bossRoom);
    }

    public ArrayList<Room> getMap() {
        return map;
    }
    public Room getFirstRoom() {
        return map.get(0);
    }
    public void clearFirstRoom() {
        map.remove(0);
    }
}
