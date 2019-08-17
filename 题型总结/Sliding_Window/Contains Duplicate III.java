/* Given an array of integers, find out whether there are two distinct indices i and j in the array such that 
the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is 
at most k. */

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
//一定范围内值与x相差t的数，一定在区间[x-t, x+t]. 换句话说，在一定范围内，如果比x大最小的数不在
//区间里，或者是比x小最大的数不在区间里，那么就不会有值在区间里了.然后想到binary search可以logn找到
//比自己大的最小值，然后想到balance BST = TreeSet
//time: O(nlogk), space: O(k)      