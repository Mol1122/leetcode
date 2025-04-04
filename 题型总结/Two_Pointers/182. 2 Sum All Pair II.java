/* Find all pairs of elements in a given array that sum to the pair the given target number. Return all the distinct pairs of values.

Assumptions

The given array is not null and has length of at least 2
The order of the values in the pair does not matter
Examples

A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]] */

//Method 1
public class Solution {
  public List<List<Integer>> allPairs(int[] nums, int target) {
      Set<List<Integer>> results = new HashSet<>();
      if (nums == null || nums.length < 2) {
          return new ArrayList<>();
      }
      Set<Integer> set = new HashSet<>();
      for (int i = 0; i < nums.length; i++) {
          if (set.contains(nums[i]) && target - nums[i] != nums[i]) {
              continue;
          }
          if (set.contains(target - nums[i])) {
              results.add(Arrays.asList(target - nums[i], nums[i]));
          }
          set.add(nums[i]);
      }
      return new ArrayList<>(results);
  }
}
//time: O(n), space: O(n)
//难点：有可能两个数相同[4, 4]

//Method 2
public class Solution {
  public List<List<Integer>> allPairs(int[] nums, int target) {
    List<List<Integer>> results = new ArrayList<>();
    if (nums == null || nums.length < 2) {
      return results;
    }
    Arrays.sort(nums);
    int start = 0, end = nums.length - 1;

    while (start < end) {
      if (nums[start] + nums[end] == target) {
        results.add(Arrays.asList(nums[start], nums[end]));
        
        int left = nums[start], right = nums[end];
        while (start < end && nums[start] == left) {
          start++;
        }
        while (start < end && nums[end] == right) {
          end--;
        }
      } else if (nums[start] + nums[end] < target) {
        start++;
      } else {
        end--;
      }
    }
    return results;
  }
}
//time: O(nlogn), space: O(1)