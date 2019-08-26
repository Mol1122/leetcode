/* Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Example
Example 1

Input : [100, 4, 200, 1, 3, 2]
Output : 4
Explanation : The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length:4
Clarification
Your algorithm should run in O(n) complexity. */

public class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i] - 1)) {
                int count = 0;
                int curr = nums[i];
                
                while (set.contains(curr)) {
                    count++;
                    curr++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
}
//time: O(n), space: O(n)