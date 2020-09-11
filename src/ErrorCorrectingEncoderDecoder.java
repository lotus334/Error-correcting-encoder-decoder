import java.util.Random;
import java.util.Scanner;

public class ErrorCorrectingEncoderDecoder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        System.out.println(encryptText(inputText));
    }

    static boolean isValid(int asciiNum) {
        boolean valid = false;
        if ((asciiNum > 47 && asciiNum < 58) || (asciiNum > 64 && asciiNum < 91) || (asciiNum > 96 && asciiNum < 123)) {
            valid = true;
        }
        return valid;
    }

    static String encryptText(String inputText) {
        //itereting throug stringarray and generating error code
        Random random = new Random();
        StringBuilder encrypted = new StringBuilder(inputText);
        for (int i = 0; i < inputText.length(); i += 3) {
            int asciiNum = random.nextInt(122 - 65 + 1) + 65;
            while (!isValid(asciiNum)) {
                asciiNum = random.nextInt(122 - 65 + 1) + 65;
            }
            int incr = random.nextInt(3);
            encrypted.replace(i + incr, i + incr + 1, String.valueOf((char) asciiNum));
        }
        return encrypted.toString();
    }
}