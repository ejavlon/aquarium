package uz.ejavlon.models;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Aquarium {

    public static List<Fish> fishes = new CopyOnWriteArrayList<>();
    public static final Coordinate minPosition = new Coordinate(0,0);
    public static final Coordinate maxPosition = new Coordinate(20,10);

}
