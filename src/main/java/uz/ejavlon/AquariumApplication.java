package uz.ejavlon;


import uz.ejavlon.models.Aquarium;
import uz.ejavlon.models.Fish;
import uz.ejavlon.service.FishService;

import java.util.*;

public class AquariumApplication {
    public static void main(String[] args) {

        Random random = new Random();

        int n = random.nextInt(20) + 1;
        int m = random.nextInt(20) + 1;

        FishService.build(n, m);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                System.out.println("\n\n--------------------------------------------------------------------------------------------------------");
                System.out.println("\nBaliqlar xaqida ma'lumot:");
                for (Fish fish : Aquarium.fishes) {
                    System.out.println(fish);
                }

                String info = FishService.info();
                System.out.println("\n"+info);

                System.out.println("\nBaliqlar boshqa koordinataga ko'chdi:\n");
                for (Fish fish : Aquarium.fishes) {
                    new Thread(fish).start();
                }
            }
        },0,15000);

    }
}