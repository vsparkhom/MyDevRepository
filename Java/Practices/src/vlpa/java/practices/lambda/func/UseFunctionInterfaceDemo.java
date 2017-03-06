package vlpa.java.practices.lambda.func;

import java.util.function.Function;

public class UseFunctionInterfaceDemo {

    public static void main(String[] args) {

        Function<Integer, Integer> f = n -> {
            int factorial = 1;
            for(int i=1; i<=n; i++) {
               factorial *= i;
            }
            return factorial;
        };

        System.out.println("factorial of 3 is: " + f.apply(3));
        System.out.println("factorial of 5 is: " + f.apply(5));

    }

}
