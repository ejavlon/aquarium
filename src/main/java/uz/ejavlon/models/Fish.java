package uz.ejavlon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Fish implements Runnable{

    private Integer id;

    private Coordinate coordinate;

    private Integer lifeSpan;

    private final Gender gender;

    private List<Fish> parents;

    private List<Fish> childes;

    public Fish(Gender gender) {
        Random random = new Random();

        this.id = Aquarium.fishes.size() + 1;
        this.coordinate = move();
        this.lifeSpan = random.nextInt(30) + 1;
        this.gender = gender;
        this.childes = new ArrayList<>();
    }

    @Override
    public void run() {
        if (this.lifeSpan == 1) {
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

    public List<Fish> getParents() {
        return parents;
    }

    public void setParents(List<Fish> parents) {
        this.parents = parents;
    }

    public List<Fish> getChildes() {
        return childes;
    }

    public void setChildes(List<Fish> childes) {
        this.childes = childes;
    }

    public void addChild(Fish newChild){
        childes.add(newChild);
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", coordinate=" + coordinate +
                ", lifeSpan=" + lifeSpan +
                ", gender=" + gender +
                ", parents=" + parents +
                ", childes=" + childes +
                '}';
    }
}
