package com.weon.devel.utils;

import java.util.Random;

public class Utils {
    private static final Random random = new Random();

    public static int generateRandomId() {
        return random.nextInt(10000);
    }
}