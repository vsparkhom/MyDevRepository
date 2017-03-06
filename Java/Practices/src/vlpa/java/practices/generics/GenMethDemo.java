package vlpa.java.practices.generics;

import java.util.Arrays;

public class GenMethDemo {

    public static <T extends Comparable<T>, V extends T> boolean isIn(T x, V[] y) {
       for (int i=0; i<y.length; i++) {
           if (x.equals(y[i])) {
               return true;
           }
       }
       return false;
    }

    public static void main(String[] args) {
        Integer nums[] = {1, 2, 3, 4, 5};
        Integer n = 3;

        System.out.printf("%d %s in %s", n, (isIn(n, nums) ? "exists" : "doesn't exist"), Arrays.toString(nums));

//        Ge n < I n t e g e r > ge n s [ J = n e w Gen< I n t eg e r > [ l O ] ;

        Gen<?> gens[] = new Gen<?>[10];

    }

    public class Gen<T extends Number> {
        T ob;

        public Gen(T ob) {
            this.ob = ob;
        }
    }
}
