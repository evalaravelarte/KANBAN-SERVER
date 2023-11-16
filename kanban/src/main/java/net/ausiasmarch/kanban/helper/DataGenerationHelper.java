package net.ausiasmarch.kanban.helper;

import java.util.Random;

public class DataGenerationHelper {

    private static final String[] aUsernames = { "mariposa23", "astro_girl","thunder_bolt", "zen_master","pixel_ninja", "guitar_maestro", "sunny_dayz", "moonlight_runner", "neon_panda",
        "velocity_rider","ocean_dreamer", "firefly_quest", "arctic_explorer", "jungle_wanderer", "cosmic_traveler", "mystic_scribe", "echo_voyager" };


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
