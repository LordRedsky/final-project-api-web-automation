package com.redsky.web.utility;

import java.util.Random;

public class RandomAlphabet {
    public static String generate(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomAlphabet = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            randomAlphabet.append(alphabet.charAt(index));
        }

        return randomAlphabet.toString();
    }
}
