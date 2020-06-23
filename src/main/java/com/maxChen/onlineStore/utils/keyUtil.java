package com.maxChen.onlineStore.utils;

import java.util.Random;

public class keyUtil {

    /** generate unique primary key */

    public static synchronized String genUniqueKey() {

        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
