public class Solution {
  public boolean existSum(int[] nums, int target) {
      if (nums == null || nums.length < 2) {
          return false;
      }
      Map<Integer, Integer> val2index = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
          if (val2index.containsKey(target - nums[i])) {
              return true;
          }
          val2index.put(nums[i], i);
      }
      return false;
  }
}
//time: O(n), space: O(n)