public class Solution {
  public List<List<Integer>> allTriples(int[] nums, int target) {
      List<List<Integer>> results = new ArrayList<>();
      if (nums == null || nums.length < 3) {
          return results;
      }
      Arrays.sort(nums);
      for (int i = 0; i < nums.length; i++) {
          if (i > 0 && nums[i] == nums[i - 1]) {
              continue;
          }
          twoSum(nums, i + 1, nums.length - 1, target - nums[i], nums[i], results);
      }
      return results;
  }
  
  private void twoSum(int[] nums, int start, int end, int target, int number, List<List<Integer>> results) {
      while (start < end) {
          if (nums[start] + nums[end] == target) {
              results.add(Arrays.asList(number, nums[start], nums[end]));
              start++;
              end--;
              while (start < end && nums[start] == nums[start - 1]) {
                  start++;
              }
              while (start < end && nums[end] == nums[end + 1]) {
                  end--;
              }
          } else if (nums[start] + nums[end] < target) {
              start++;
          } else {
              end--;
          }
      }
  }
}
//time: O(n^2), space: O(1)
