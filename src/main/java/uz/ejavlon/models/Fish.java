package uz.ejavlon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Fish implements Runnable{

    private final Integer id;

    private Coordinate coordinate;

    private Integer lifeSpan;

    private final Gender gender;


    public Fish(Gender gender) {
        Random random = new Random();

        this.id = Aquarium.fishes.size() + 1;
        this.coordinate = move();
        this.lifeSpan = random.nextInt(30) + 1;
        this.gender = gender;
    }

    @Override
    public void run() {
        if (this.lifeSpan == 0) {
            Aquarium.fishes.remove(this);
            System.out.printf("%s - id'lik baliq o'ldi.\n",this.getId());
        }
        else{
            this.lifeSpan--;
            this.coordinate = move();
            System.out.printf("%s - id'lik %s %s'ga harakatlandi.\n",id,gender.getDescription(),coordinate);
        }

    }

    public Coordinate move(){
        Random random = new Random();
        int xPosition = random.nextInt(Aquarium.maxPosition.getX() + 1);
        int yPosition = random.nextInt(Aquarium.maxPosition.getY() + 1);

        return new Coordinate(xPosition,yPosition);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Integer getId() {
        return id;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(Integer lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", lifeSpan=" + lifeSpan +
                ", gender=" + gender +
                '}';
    }
}
