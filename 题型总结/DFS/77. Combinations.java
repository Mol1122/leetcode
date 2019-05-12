public class Solution {
  public List<List<Integer>> combine(int n, int k) {
      List<List<Integer>> results = new ArrayList<>();
      if (n < 1 || k > n) {
          return results;
      }
      dfs(n, 1, k, new ArrayList<>(), results);
      return results;
  }
  
  private void dfs(int n, int index, int k, List<Integer> combination, 
                   List<List<Integer>> results) {
      if (combination.size() == k) {
          results.add(new ArrayList<>(combination));
          return;
      }
      for (int i = index; i <= n; i++) {
          combination.add(i);
          dfs(n, i + 1, k, combination, results);
          combination.remove(combination.size() - 1);
      }
  }
}

/* 时间复杂度：O(n^k)
** 空间复杂度：O(k) */