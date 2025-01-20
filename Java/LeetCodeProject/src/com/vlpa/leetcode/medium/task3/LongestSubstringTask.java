package com.vlpa.leetcode.medium.task3;

public class LongestSubstringTask {

    public int lengthOfLongestSubstring(String s) {
        System.out.println("input: " + s);
        int maxLength = -1;

        for (int i = 0; i < s.length(); i++) {

            int n = 1;
            String buffer = "";

            while (i + n - 1 < s.length()) {

                int indexTo = i + n - 1;
                String symbol = s.substring(indexTo, indexTo + 1);
//                System.out.println("i: " + i + ", n: " + n + ", indexTo: " + indexTo);
//                System.out.println("buffer: '" + buffer + "', symbol: '" + symbol + "'");

                if (!buffer.contains(symbol)) {
                    buffer = buffer + symbol;
                    if (maxLength < n) {
//                        System.out.println("   - update max to " + n);
                        maxLength = n;
                    }
                } else {
//                    System.out.println("   - break sequence");
                    break;
                }

                n++;
            }

        }

        System.out.println("return: " + maxLength);
        return maxLength;
    }

}
