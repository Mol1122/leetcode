package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
	    int[] nums = {5,2,1,1,1,1,2, 4};
	    int[] results = m.mergeArray(nums);
	    for (int i = 0; i < results.length; i++) {
	        System.out.print(results[i] + " ");
        }
    }

    //merge two consective numbers and plus one if they have the same value
    //[5,2,1,1,1,1,2, 4] -> [5, 2,2,2,2,4] -> [5,3,3,4] -> [5,4,4] -> [5,5] -> [6]
    public int[] mergeArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                list.add(nums[i] + 1);
                i++;
            } else {
                list.add(nums[i]);
            }
        }
        if (nums[nums.length - 1] != nums[nums.length - 2]) {
            list.add(nums[nums.length - 1]);
        }
        boolean hasSame = false;
        int[] results = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
            if (i > 0 && list.get(i) == list.get(i - 1)) {
                hasSame = true;
            }
        }

        if (hasSame) {
            return mergeArray(results);
        }
        return results;
    }
}
