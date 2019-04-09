public class Solution {
  public List<List<Integer>> combinations(int target, int[] coins) {
      List<List<Integer>> results = new ArrayList<>();
      if (coins == null || coins.length == 0) {
          return results;
      }
      dfs(coins, 0, target, new ArrayList<>(), results);
      return results;
  }
  
  private void dfs(int[] coins, int index, int target, List<Integer> combination, 
                   List<List<Integer>> results) {
      if (index == coins.length - 1) { //到达最后一层/最后一个数
          if (target % coins[coins.length - 1] == 0) { //能除尽的话
              combination.add(target / coins[coins.length - 1]);
              results.add(new ArrayList<>(combination));
              combination.remove(combination.size() - 1);
          }
          return;
      }
      for (int i = 0; i * coins[index] <= target; i++) { //i表示coins[index]选几个
          combination.add(i);
          dfs(coins, index + 1, target - i * coins[index], combination, results);
          combination.remove(combination.size() - 1);
      }
  }
}

/* 时间复杂度：O(target^coins.length)
** 空间复杂度：O(coins.length) */
