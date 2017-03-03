package vlpa.java.practices.lambda;

public class MyStringOps {

    public String reverseString(String s) {
        StringBuilder reversedString = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            reversedString.append(s.charAt(i));
        }
        return reversedString.toString();
    }

}