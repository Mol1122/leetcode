/* Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.

Assumptions

The given array is not null and has length of at least 2.

Examples

A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]

A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]] */
public class Solution {
  public List<List<Integer>> allPairs(int[] nums, int target) {
      List<List<Integer>> results = new ArrayList<>();
      if (nums == null || nums.length < 2) {
          return results;
      }
      Map<Integer, List<Integer>> num2index = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
          if (num2index.containsKey(target - nums[i])) {
              for (int index : num2index.get(target - nums[i])) {
                  results.add(Arrays.asList(index, i));
              }
          } 
          num2index.putIfAbsent(nums[i], new ArrayList<>());
          num2index.get(nums[i]).add(i);
      }
      return results;
  }
}

//time: O(n^2), space: O(n)
