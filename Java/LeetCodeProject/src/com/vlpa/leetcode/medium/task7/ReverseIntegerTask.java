package com.vlpa.leetcode.medium.task7;

/**
 * https://leetcode.com/problems/reverse-integer/description/
 */
public class ReverseIntegerTask {

    public int reverse(int x) {
        System.out.println("initial number: " + x);

        boolean isNegative = x < 0;

        String value = "" + x;
        String s = value.substring(isNegative ? 1 : 0);
        System.out.println("s: " + s);
        s = new StringBuilder(s).reverse().toString();

        Long result = Long.valueOf(s);

        if (isNegative) {
            result = -result;
        }

        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE ) {
            System.out.println("Too small/big number");
            return 0;
        }

        return result.intValue();
    }

    // OTHER SOLUTIONS

//    public int reverse(int x) {
//        long ans = 0;
//
//        while (x != 0){
//            ans = ans * 10 + x % 10;
//            x /= 10;
//
//        }
//        return (ans< Integer.MIN_VALUE|| ans> Integer.MAX_VALUE) ? 0 : (int) ans;
//    }
}
