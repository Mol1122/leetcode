/* Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums toT. Each number in C may only be used once in the combination.

All numbers (including target) will be positive integers.

Elements in a combination (a1, a2, … , ak) must be in non-descending order.

The solution set must not contain duplicate combinations.

Example

          given candidate set 10,1,2,7,6,1,5 and target 8,

          A solution set is:

          [1, 7]

          [1, 2, 5]

          [2, 6]

          [1, 1, 6] */

//二叉树式解法
class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        Arrays.sort(nums);
        dfs(nums, 0, 0, target, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(int[] nums, int index, int currSum, int target, List<Integer> temp, List<List<Integer>> results) {  
        if (currSum >= target) {
            if (currSum == target) {
                results.add(new ArrayList<>(temp));
            }
            return;
        }
        if (index == nums.length) {
            return;
        }
        
        temp.add(nums[index]);
        dfs(nums, index + 1, currSum + nums[index], target, temp, results);
        temp.remove(temp.size() - 1);
        
        while (index < nums.length - 1 && nums[index] == nums[index + 1]) {
            index++;
        }
        
        dfs(nums, index + 1, currSum, target, temp, results);
    }
}
//time: O(2^n), space: O(n)          
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

  private void dfs(int[] nums, int startIndex, int target, List<Integer> combination, 
                   List<List<Integer>> results) {
    if (target == 0) {
      results.add(new ArrayList<>(combination));
      return;
    }
    if (startIndex == nums.length) {
      return;
    }
    combination.add(nums[startIndex]);
    dfs(nums, startIndex + 1, target - nums[startIndex], combination, results);
    combination.remove(combination.size() - 1);


    while (startIndex + 1 < nums.length && nums[startIndex + 1] == nums[startIndex]) {
      startIndex++;
    }
    dfs(nums, startIndex + 1, target, combination, results);
  }
}

//time: O(n^target), space: O(target)          
public class Solution {
  public List<List<Integer>> combinationSum2(int[] nums, int target) {
    Set<List<Integer>> results = new HashSet<>();
    if (nums == null || nums.length == 0) {
      return new ArrayList<>(results);
    }
    Arrays.sort(nums);
    dfs(nums, 0, target, new ArrayList<>(), results);
    return new ArrayList<>(results);
  }

  private void dfs(int[] nums, int startIndex, int target, List<Integer> combination,
                   Set<List<Integer>> results) {
    if (target == 0) {
      results.add(new ArrayList<>(combination));
      return;
    }
    if (startIndex == nums.length) {
      return;
    }
    for (int i = startIndex; i < nums.length; i++) {
      combination.add(nums[i]);
      dfs(nums, i + 1, target - nums[i], combination, results);
      combination.remove(combination.size() - 1);
    }
  }
}

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
