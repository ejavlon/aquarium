package uz.ejavlon.service;

import uz.ejavlon.models.Aquarium;
import uz.ejavlon.models.Coordinate;
import uz.ejavlon.models.Fish;
import uz.ejavlon.models.Gender;

import java.util.Collections;
import java.util.List;

public class FishService {


    public static Fish[] build(int count,Gender gender){
        Fish [] fishes = new Fish[count];
        for (int i = 0; i < count; i++) {
            fishes[i] = new Fish(gender);
        }

        return fishes;
    }

    public static void build(int n, int m){
        for (int i = 0; i < n; i++) {
            Aquarium.fishes.add(new Fish(Gender.MALE));
        }

        for (int i = 0; i < m; i++) {
            Aquarium.fishes.add(new Fish(Gender.FEMALE));
        }
    }

    public static String info(){
        int oldSize = Aquarium.fishes.size();
        StringBuilder stringBuilder = new StringBuilder();
        List<Coordinate> coordinates = Aquarium.fishes.stream().map(Fish::getCoordinate).toList();

        for (int i = 0; i < coordinates.size(); i++) {
            int count = Collections.frequency(coordinates, coordinates.get(i));
            if (count > 1) checkCoordinate(coordinates,i,stringBuilder);
        }
        System.out.printf("\nBaliqlar soni %s taga ko'paydi.",Aquarium.fishes.size()-oldSize);
        return stringBuilder.toString();
    }

    private static void checkCoordinate(List<Coordinate> coordinates, int start, StringBuilder stringBuilder) {

        for (int i = start+1; i < coordinates.size(); i++) {
            if (coordinates.get(i).equals(coordinates.get(start))
                && Aquarium.fishes.get(i).getGender() != Aquarium.fishes.get(start).getGender()){

                Fish newFish = new Fish(Gender.genereteGender());
                Aquarium.fishes.add(newFish);

                Fish parent1 = Aquarium.fishes.get(i);
                Fish parent2 = Aquarium.fishes.get(start);

                stringBuilder.append(String.format("%s - id'lik %s bilan %s - id'lik %s uchrashdi \n va %s - id'lik %s tug'ildi.\n\n",
                        parent1.getId(), parent1.getGender().getDescription(),
                        parent2.getId(), parent2.getGender().getDescription(),
                        newFish.getId(),newFish.getGender().getDescription()
                        ));
            }
        }
    }
}