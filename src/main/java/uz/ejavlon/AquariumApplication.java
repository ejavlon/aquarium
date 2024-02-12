package uz.ejavlon;


import uz.ejavlon.models.Aquarium;
import uz.ejavlon.models.Fish;
import uz.ejavlon.models.service.FishService;

import java.util.*;

public class AquariumApplication {
    public static void main(String[] args) {

        Random random = new Random();

        int n = random.nextInt(20) + 1;
        int m = random.nextInt(20) + 1;

        FishService.build(n, m);


        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                System.out.println("\nBaliqlar xaqida ma'lumot:");
                for (Fish fish : Aquarium.fishes) {
                    System.out.println(fish);
                }

                String info = FishService.info();
                System.out.println(info);

                System.out.println("\n\nBaliqlar boshqa koordinataga ko'chdi:\n");
                for (Fish fish : Aquarium.fishes) {
                    new Thread(fish).start();
                }
            }
        },0,15000);

    }
}