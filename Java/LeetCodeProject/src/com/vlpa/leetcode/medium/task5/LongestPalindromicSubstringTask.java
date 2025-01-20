package com.vlpa.leetcode.medium.task5;

//https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstringTask {

    public String longestPalindrome(String s) {
        System.out.println("input: " + s);
        long startTime = System.currentTimeMillis();
        String longest = "";

        for (int i = 0; i < s.length(); i++) {

            int n = 1;
//            String buffer = "";

            while (i + n - 1 < s.length()) {

                int indexTo = i + n - 1;
                String seq = s.substring(i, indexTo + 1);
//                System.out.println("i: " + i + ", n: " + n + ", indexTo: " + indexTo);

                String seqReversed = new StringBuilder(seq).reverse().toString();
//                System.out.println("seq: '" + seq + "', serReversed: '" + seqReversed + "'");

                if (seq.equals(seqReversed)) {
//                    System.out.println("   - sequence '" + seq + "' is a palindrome");
                    if (longest.length() < seq.length()) {
//                        System.out.println("   - update longest to " + seq);
                        longest = seq;
                    }
                }
//                System.out.println();
                n++;
            }

        }

        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("longest: " + longest + ", took: " + elapsedTime + " ms");
        return longest;
    }
}
