/* Given an integer number, return all possible combinations of the factors that can multiply to the target number.

Example

Give A = 24

since 24 = 2 x 2 x 2 x 3

              = 2 x 2 x 6

              = 2 x 3 x 4

              = 2 x 12

              = 3 x 8

              = 4 x 6

your solution should return

{ { 2, 2, 2, 3 }, { 2, 2, 6 }, { 2, 3, 4 }, { 2, 12 }, { 3, 8 }, { 4, 6 } }

note: duplicate combination is not allowed. */

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

//另一种解法，时间复杂度为: O(n^target), 空间复杂度为: O(target), 但是写起来比较复杂