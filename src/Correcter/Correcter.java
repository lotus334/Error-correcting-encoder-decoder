package Correcter;

import java.util.Random;
import java.util.Scanner;

public class Correcter {
    private String text;
    private String tripledText;
    private String encryptedText;
    private String decryptedText;

    public Correcter() {
        text = new Scanner(System.in).nextLine();
        tripledText = tripleText();
        encryptedText = encryptText(3);
        decryptedText = decryptText();
    }

    private String tripleText() {
        StringBuilder tripledText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < 3; j++) {
                tripledText.append(text.charAt(i));
            }
        }
        return tripledText.toString();
    }

    private String encryptText(int errorPerChar) {
        //iterate through string and generate error code
        Random random = new Random();
        StringBuilder encrypted = new StringBuilder(tripledText);
        for (int i = 0; i < encrypted.length(); i += 3) {
            int asciiNum = random.nextInt(122 - 65 + 1) + 65;
            while (!isValid(asciiNum)) {
                asciiNum = random.nextInt(122 - 65 + 1) + 65;
            }
            int randomIncrement = random.nextInt(errorPerChar);
            encrypted.replace(i + randomIncrement, i + randomIncrement + 1, String.valueOf((char) asciiNum));
        }
        deleteExtraChars(encrypted, tripledText.length());
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

    private String decryptText() {
        StringBuilder decryptedText = new StringBuilder();
        char first;
        char second;
        char third;
        for (int i = 0; i < encryptedText.length(); i += 3) {
            first = encryptedText.charAt(i);
            second = encryptedText.charAt(i + 1);
            third = encryptedText.charAt(i + 2);
            if (first == second) {
                decryptedText.append(first);
            } else if (second == third) {
                decryptedText.append(second);
            } else if (first == third) {
                decryptedText.append(third);
            }
        }
        return decryptedText.toString();
    }

    String  getText() {
        return text;
    }
    String getTripledText() {
        return tripledText;
    }
    String getEncryptedText() {
        return encryptedText;
    }
    String getDecryptedText() {
        return decryptedText;
    }
}