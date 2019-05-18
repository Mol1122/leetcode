/* Determine if there exists a set of four elements in a given array that sum to the given target number.

Assumptions

The given array is not null and has length of at least 4
Examples

A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)

A = {1, 2, 2, 3, 4}, target = 12, return false */
public class Solution {
  public boolean exist(int[] nums, int target) {
      if (nums == null || nums.length < 4) {
          return false;
      }
      Map<Integer, int[]> sum2index = new HashMap<>();
    
      for (int j = 1; j < nums.length; j++) { //right index of second pair
          for (int i = 0; i < j; i++) { //left index of second pair
              int sum = nums[i] + nums[j];
              //make sure no duplicates
              if (sum2index.containsKey(target - sum) && sum2index.get(target - sum)[1] < i) {
                  return true;
              }
              if (!sum2index.containsKey(sum)) {
                  sum2index.put(sum, new int[]{i, j});
              }
          }
      }
      return false;
  }
}
//time: O(n^2), space: O(n)