package vlpa.java.practices.lambda;

public class LambdasAsArgumentsDemo {

    public static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {

        String strIn = "This is a test string";
        System.out.println("Initial string: " + strIn);

        //case 1 - convert string to upper case
        System.out.println("case 1 (upper case) result string: " + stringOp((s) -> s.toUpperCase(), strIn));

        //case 2 - remove spaces from the string
        System.out.println("case 2 (remove spaces) result string: "
                + stringOp(
                s -> {
                    String result = "";
                    result = s.replaceAll(" ", "");
                    return result;
                }, strIn));

        //case 3 - test references - reverse string
        MyStringOps ops = new MyStringOps();
        System.out.println("case 3 (reverse string) result string: " + stringOp(ops::reverseString, strIn));

    }
}


