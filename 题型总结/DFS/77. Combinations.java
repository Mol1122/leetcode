/* Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
] */

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        dfs(n, 1, k, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(int n, int index, int k, List<Integer> combination, List<List<Integer>> results) {
        if (combination.size() == k) {
            results.add(new ArrayList<>(combination));
            return;
        }
        if (index > n) {
            return;
        }
        combination.add(index);
        dfs(n, index + 1, k, combination, results);
        combination.remove(combination.size() - 1);
        
        dfs(n, index + 1, k, combination, results);
    }
}
//time: O(2^n), space: O(n)

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