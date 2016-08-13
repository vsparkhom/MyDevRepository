package vlpa.ch57.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Version2.
 *  Constraint: Returns chars number in string.
 *  Challenge 1: Check for string is not empty.
 */
public class CountNumberCharacters {

    public static int getCharsCount(String s) {
        return s.length();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What is the input string?");

        boolean isEmpty = false;
        do {
            String inputStr = reader.readLine();
            isEmpty = (inputStr == null || "".equals(inputStr));
            if (isEmpty) {
                System.out.println("String is empty. Please enter it one more time:");
            } else {
                int charsCount = CountNumberCharacters.getCharsCount(inputStr);
                System.out.println("Chars count: " + charsCount);
            }
        } while (isEmpty);

    }

}
