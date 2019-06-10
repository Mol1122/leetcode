/* Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct. */

public class Solution {
  public boolean containsDuplicate(int[] nums) {
    if (nums == null || nums.length == 0) {
        return false;
    }
    Set<Integer> hash = new HashSet<>();
    for (int num : nums) {
        if (hash.contains(num)) {
            return true;
        }
        hash.add(num);
    }
    return false;
  }
}
//time: O(n), space: O(n)
/*  如果排好序，非常简单，for loop依次与旁边元素比较，完全不用HashSet。
    如果没排好序，那么有多种选择：一是可以先排序 time: O(nlogn), space: O(1)，回到上一种解法。二才是使用HashSet time: O(n), space: O(n)。 */