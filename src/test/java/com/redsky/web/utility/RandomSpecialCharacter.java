package com.redsky.web.utility;

import java.util.Random;

public class RandomSpecialCharacter {
    public static String generate(int length) {
        String spacialCharacter = "`~!@#$%^&*()_+-=[{]}|;:',></?";
        StringBuilder randomSpecialCharacter = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(spacialCharacter.length());
            randomSpecialCharacter.append(spacialCharacter.charAt(index));
        }

        return randomSpecialCharacter.toString();
    }
}
