package com.sunny.leetcode;

import com.sunny.common.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunshah on 9/28/16.
 */
public class TwoSum {

    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

     You may assume that each input would have exactly one solution.

     Example:
     Given nums = [2, 7, 11, 15], target = 9,

     Because nums[0] + nums[1] = 2 + 7 = 9,
     return [0, 1].
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] indices = null;

        if(nums == null) {
            return indices;
        }

        Map<Integer, Integer> numsIndiceMap = new HashMap(nums.length);

        for(int i = 0; i < nums.length; i++) {
            if(numsIndiceMap.get(target - nums[i]) != null) {
                indices = new int[2];
                indices[0] = i;
                indices[1] = numsIndiceMap.get(target-nums[i]);
                Arrays.sort(indices);
            }
            /* keep map of number at index */
            numsIndiceMap.put(nums[i], i);
        }

        return indices;
    }

    public static void main(String[] args) {
        int[] nums = {0, 7, 11, 0};
        int target = 0;

        int[] indices = twoSum(nums, target);

        Logger.logArray(indices);
    }
}
