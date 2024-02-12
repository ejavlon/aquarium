package uz.ejavlon.models;

import java.util.Random;

public enum Gender {
    MALE("Erkak baliq"),
    FEMALE("Urg'ochi baliq");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Gender genereteGender(){
        Random random = new Random();
        int index = random.nextInt(2);

        return values()[index];
    }

}
