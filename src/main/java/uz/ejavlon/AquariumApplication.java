package uz.ejavlon;

import uz.ejavlon.models.Aquarium;
import java.util.Random;

public class AquariumApplication {
    public static void main(String[] args) {

        Random random = new Random();

        int n = random.nextInt(20) + 1;
        int m = random.nextInt(20) + 1;

        Aquarium aquarium = new Aquarium(n, m);
        new Thread(aquarium).start();

    }
}