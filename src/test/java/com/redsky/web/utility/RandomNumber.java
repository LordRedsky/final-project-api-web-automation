package com.redsky.web.utility;

import java.util.Random;

public class RandomNumber {
    public static String generate(int length) {
        String number = "1234567890";
        StringBuilder randomNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(number.length());
            randomNumber.append(number.charAt(index));
        }

        return randomNumber.toString();
    }
}
