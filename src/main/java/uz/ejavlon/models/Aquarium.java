package uz.ejavlon.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aquarium implements Runnable {
    public static final Coordinate minPosition = new Coordinate(0,0);
    public static final Coordinate maxPosition = new Coordinate(20,10);

    private final List<Fish> fishes;

    public Aquarium(int n, int m) {
        this.fishes = Collections.synchronizedList( new ArrayList<>());
        generateFish(n,m);
    }

    @Override
    public void run() {
        boolean anyMatch;
        int loopCount = 1;

        do{
            System.out.printf("\nCamera info(%s):\n%s\n ",loopCount,cameraInfo());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            loopCount++;
            anyMatch = fishes.stream().anyMatch(fish -> fish.getLifeSpan() > 0);
        }while (anyMatch);

        System.out.println("Barcha baliqlar o'ldi!");
    }
    private void generateFish(int n, int m){
        new Thread(() -> {
            Fish fish;
            for (int i = 0; i < n; i++) {
                fish = new Fish(Gender.MALE,this);
                fishes.add(fish);
                new Thread(fish).start();
            }

            for (int i = 0; i < m; i++) {
                fish = new Fish(Gender.FEMALE,this);
                fishes.add(fish);
                new Thread(fish).start();
            }
        }).start();
    }

    private String cameraInfo(){
        StringBuilder stringBuilder = new StringBuilder();
        synchronized (fishes){
            long maleFishCount = fishes.stream().filter(fish -> fish.getLifeSpan() != 0 && fish.getGender() == Gender.MALE).count();
            List<Fish> aLiveFishes = new ArrayList<>(fishes.stream().parallel().filter(fish -> fish.getLifeSpan() != 0).toList());

            if (maleFishCount == aLiveFishes.size() || maleFishCount == 0)
                return maleFishCount == 0 ? stringBuilder.append("Barcha tirik baliqlar urg'ochi").toString() : stringBuilder.append("Barcha tirik baliqlar erkak").toString();

            Collections.sort(aLiveFishes);
            checkCoordinate(aLiveFishes,stringBuilder);

            return stringBuilder.isEmpty() ? stringBuilder.append("Baliqlar ko'paymadi.\n").toString() : stringBuilder.toString();
        }
    }

    private void  checkCoordinate(List<Fish>aLiveFishes,StringBuilder stringBuilder){
        Fish fish1;
        Fish fish2;
        for (int i = 0; i < aLiveFishes.size()-1; i++) {
            for (int j = i+1; j < aLiveFishes.size(); j++) {
                fish1 = aLiveFishes.get(i);
                fish2 = aLiveFishes.get(j);

                if (!fish1.getCoordinate().equals(fish2.getCoordinate()))
                    break;

                Fish newFish = new Fish(Gender.genereteGender(),this);
                fish1.addChild(newFish);
                fish2.addChild(newFish);
                newFish.addParent(fish1);
                newFish.addParent(fish2);

                fishes.add(newFish);
                new Thread(newFish).start();

                stringBuilder.append(String.format("%s - id'lik %s bilan %s - id'lik %s uchrashdi \n va %s - id'lik %s tug'ildi.\n\n",
                        fish1.getId(), fish1.getGender().getDescription(),
                        fish2.getId(), fish2.getGender().getDescription(),
                        newFish.getId(),newFish.getGender().getDescription()
                ));
            }
        }
    }

    public List<Fish> getFishes() {
        return fishes;
    }
}