package net.ausiasmarch.kanban.helper;

import java.util.Random;

public class DataGenerationHelper {

    private static final String[] aUsernames = { "mariposa23", "astrogirl","thunderbolt", "zenmaster","pixelninja", "guitarmaestro", "sunnydayz", "moonlightrunner", "neonpanda",
        "velocityrider","oceandreamer", "fireflyquest", "arcticexplorer", "junglewanderer", "cosmictraveler", "mysticscribe", "echovoyager" };


    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String getRadomUsername() {
        return aUsernames[(int) (Math.random() * aUsernames.length)];
    }

    private static Random random = new Random();

    //generate random email with aUsernames
    public static String getRandomEmail() {
        return aUsernames[(int) (Math.random() * aUsernames.length)] + "@gmail.com";
    }
}
