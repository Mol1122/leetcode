/* Given an array nums of n integers and an integer target, are there elements a, b, c, 
and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array 
which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
] */

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> results = new HashSet<>();
        Map<Integer, List<int[]>> sum2index = new HashMap<>();
        
        for (int j = 0; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                int sum = nums[i] + nums[j];
                
                if (sum2index.containsKey(target - sum)) {
                    for (int[] pair : sum2index.get(target - sum)) {
                        if (pair[1] >= i) {
                            continue;
                        }
                        List<Integer> temp = Arrays.asList(nums[pair[0]], nums[pair[1]], nums[i], nums[j]);
                        Collections.sort(temp);
                        results.add(temp);
                    }
                }
                sum2index.putIfAbsent(sum, new ArrayList<>());
                sum2index.get(sum).add(new int[]{i, j});
            }
        }
        return new ArrayList<>(results);
    }
}
//time: O(n^2), space: O(n)