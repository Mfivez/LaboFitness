package be.labofitness.labo_fitness.il.utils;

import java.util.Random;

public class Anonymizer {

    public static String CHARACTER = "abcdefghijklmnopqrstuvwxyz";

    public static String genereteRandomString(String stringToAnonymise){
        Random random = new Random();
        StringBuilder builder  =  new StringBuilder(stringToAnonymise.length());
        for(int i = 0; i < stringToAnonymise.length(); i ++){
            int randomIndex = random.nextInt(CHARACTER.length());
            char randomChar = CHARACTER.charAt(randomIndex);
            builder.append(randomChar);
        }
        return builder.toString();
    }

}
