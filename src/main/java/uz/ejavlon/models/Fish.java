package uz.ejavlon.models;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Fish implements Runnable, Comparable<Fish>{
    private static final Random random = new Random();
    private final Integer id;

    private Coordinate coordinate;

    private Integer lifeSpan;

    private final Gender gender;

    private final Set<Fish> parents;

    private final Set<Fish> childes;


    public Fish(Gender gender,Aquarium aquarium) {
        this.id = aquarium.getFishes().size() + 1;
        this.coordinate = move();
        this.lifeSpan = Fish.random.nextInt(30) + 1;
        this.gender = gender;
        this.parents = new HashSet<>();
        this.childes = new HashSet<>();
    }

    @Override
    public void run() {
        while (true){
            if (this.lifeSpan == 0) {
                System.out.printf("%s - id'lik baliq o'ldi.\n",id);
                break;
            }
            else{
                this.lifeSpan--;
                this.coordinate = move();
                System.out.printf("%s - id'lik %s %s'ga harakatlandi.\tBarcha ko'rsatgichlar:%s\n",id,gender.getDescription(),coordinate,this);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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

    public Set<Fish> getParents() {
        return parents;
    }

    public void addParent(Fish parent){
        this.parents.add(parent);
    }

    public void addChild(Fish child){
        this.childes.add(child);
    }

    private String toString(Set<Fish> fishes){
        if (fishes.size() == 0) return "";

        StringBuilder stringBuilder = new StringBuilder();
        fishes.forEach(fish -> {
            stringBuilder.append(fish.getId());
            stringBuilder.append(",");
        });
        return stringBuilder.substring(0,stringBuilder.length()-1);
    }


    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", " + coordinate +
                ", lifeSpan=" + lifeSpan +
                ", gender=" + gender +
                ", parentsId= [" + toString(parents) + "]" +
                ", childesId= [" + toString(childes) + "]" +
                '}';
    }

    @Override
    public int compareTo(Fish o) {
        if (o == null) {
            throw new NullPointerException("Cannot compare with null");
        }

        int xComparison = Integer.compare(this.coordinate.getX(), o.coordinate.getX());
        return xComparison == 0 ? Integer.compare(this.coordinate.getY(), o.coordinate.getY()) : xComparison;
    }
}