package Correcter;

import java.util.Random;
import java.util.Scanner;

public class Correcter {
    private String text;
    private int initialLength;

    public Correcter() {
        text = new Scanner(System.in).nextLine();
        initialLength = text.length();
    }

    String encryptText(int errorPerChar) {
        //iterate through string and generate error code
        Random random = new Random();
        StringBuilder encrypted = new StringBuilder(text);
        for (int i = 0; i < encrypted.length(); i += 3) {
            int asciiNum = random.nextInt(122 - 65 + 1) + 65;
            while (!isValid(asciiNum)) {
                asciiNum = random.nextInt(122 - 65 + 1) + 65;
            }
            int randomIncrement = random.nextInt(errorPerChar);
            encrypted.replace(i + randomIncrement, i + randomIncrement + 1, String.valueOf((char) asciiNum));
        }
        deleteExtraChars(encrypted, initialLength);
        return encrypted.toString();
    }
    
    private boolean isValid(int asciiNum) {
        boolean valid = false;
        if ((asciiNum > 47 && asciiNum < 58) || (asciiNum > 64 && asciiNum < 91) || (asciiNum > 96 && asciiNum < 123)) {
            valid = true;
        }
        return valid;
    }

    private StringBuilder deleteExtraChars(StringBuilder encrypted, int initialLength) {
        if (initialLength < encrypted.length()) {
            encrypted.delete(initialLength, encrypted.length());
        }
        return encrypted;
    }
}