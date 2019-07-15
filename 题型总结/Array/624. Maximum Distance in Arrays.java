/* Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array. */

class Solution {
    public int maxDistance(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) {
            return Integer.MIN_VALUE;
        }
        int result = 0, min = nums.get(0).get(0), max = nums.get(0).get(nums.get(0).size() - 1);
        
        for (int i = 1; i < nums.size(); i++) {
            result = Math.max(result, Math.max(Math.abs(nums.get(i).get(0) - max), Math.abs(nums.get(i).get(nums.get(i).size() - 1) - min)));
            min = Math.min(min, nums.get(i).get(0));
            max = Math.max(max, nums.get(i).get(nums.get(i).size() - 1));
        }
        return result;
    }
}
//time: O(n), space: O(1)