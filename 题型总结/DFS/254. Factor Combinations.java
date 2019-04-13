public class Solution {
  public List<List<Integer>> combinations(int target) {
      List<List<Integer>> results = new ArrayList<>();
      if (target <= 1) {
          return results;
      }
      dfs(target, 2, new ArrayList<>(), results);
      return results;
  }
  
  private void dfs(int target, int startIndex, List<Integer> combination, 
                   List<List<Integer>> results) {
      if (target == 1) {
          if (combination.size() > 1) {
              results.add(new ArrayList<>(combination));
          }
          return;
      }
      for (int i = startIndex; i <= target; i++) {
          if (target % i != 0) {
              continue;
          }
          combination.add(i);
          dfs(target / i, i, combination, results);
          combination.remove(combination.size() - 1);
      }
  }
}

/* 时间复杂度：O(target^n)
** 空间复杂度：O(target) */