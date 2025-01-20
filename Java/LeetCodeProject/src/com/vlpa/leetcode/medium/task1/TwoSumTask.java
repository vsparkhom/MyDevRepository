package com.vlpa.leetcode.medium.task1;

import java.util.HashMap;
import java.util.Map;

public class TwoSumTask {

    /**
     * Complexity:
     *   - Time: O(n^2)
     *   - Space: O(1)
     */
    public int[] twoSum(int[] nums, int target) {
        int[] response = new int[2];

        boolean isMatchFound = false;
        for (int i=0; i<nums.length && !isMatchFound; i++) {
            response[0] = i;
            for (int j=i+1; j<nums.length; j++) {
                System.out.println(String.format("compare [%d]=%d and [%d]=%d", i, nums[i], j, nums[j]));
                if (target - nums[i] == nums[j]) {
                    response[1] = j;
                    isMatchFound = true;
                    break;
                }
            }
        }

        System.out.println("response: " + response[0] + " - " + response[1]);
        return response;
    }

    /**
     * Complexity:
     *   - Time: O(n)
     *   - Space: O(n)
     */
    public int[] twoSumImproved(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            //current + x = target -> x = target - current
            int current = nums[i];
            int x = target - current;

            if (map.containsKey(x)) {
                System.out.println("response(improved): " + map.get(x) + " - " + i);
                return new int[]{map.get(x), i};
            }

            map.put(current, i);
        }

        return null;
    }

}
