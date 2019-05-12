public class Solution {
  public List<List<Integer>> combinationSum2(int[] nums, int target) {
      List<List<Integer>> results = new ArrayList<>();
      if (nums == null || nums.length == 0) {
          return results;
      }
      Arrays.sort(nums);
      dfs(nums, 0, target, new ArrayList<>(), results);
      return results;
  }
  
  private void dfs(int[] nums, int startIndex, int target, 
                   List<Integer> combination, 
                   List<List<Integer>> results) {
      if (target == 0) {
          results.add(new ArrayList<>(combination));
          return;
      }
      for (int i = startIndex; i < nums.length; i++) {
          if (nums[i] > target) {
              break;
          }
          if (i != 0 && nums[i] == nums[i - 1] && i != startIndex) {
              continue;
          }
          combination.add(nums[i]);
          dfs(nums, i + 1, target - nums[i], combination, results);
          combination.remove(combination.size() - 1);
      }
  }
}
