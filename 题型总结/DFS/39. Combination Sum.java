public class Solution {
  public List<List<Integer>> combinationSum(int[] nums, int target) {
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
      if (startIndex == nums.length - 1) {
          if (target % nums[nums.length - 1] == 0) {
              combination.add(target / nums[nums.length - 1]);
              List<Integer> list = new ArrayList<>();
              int index = 0;
              for (int count : combination) {
                  for (int i = 0; i < count; i++) {
                      list.add(nums[index]);
                  }
                  index++;
              }
            results.add(list);
            combination.remove(combination.size() - 1); //吃了的要吐出来，不要忘了
          }
          return;
      }
      
      for (int i = 0; i * nums[startIndex] <= target; i++) {
          combination.add(i);
          dfs(nums, startIndex + 1, target - i * nums[startIndex], 
              combination, results);
          combination.remove(combination.size() - 1);
      }
  }
}

/* 时间复杂度：O(target^nums.length)
** 空间复杂度：O(nums.length) */
