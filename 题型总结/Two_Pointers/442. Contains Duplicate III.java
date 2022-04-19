/* Given an array of integers, find out whether there are two distinct indices i and j in the array such that 
the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j 
is at most k. */

public class Solution {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (nums == null || nums.length == 0) {
      return false;
    }
    TreeSet<Integer> set = new TreeSet<>();

    for (int i = 0; i < nums.length; i++) {
      Integer ceil = set.ceiling(nums[i]);
      if (ceil != null && ceil - nums[i] <= t) {
        return true;
      }
      Integer floor = set.floor(nums[i]);
      if (floor != null && nums[i] - floor <= t) {
        return true;
      }
      set.add(nums[i]);
      if (i >= k) {
        set.remove(nums[i - k]);
      }
    }
    return false;
  }
}
//time: O(nlogk), space: O(k)
