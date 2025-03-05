/* Given two arrays A and B, determine whether or not there exists a pair of elements, one drawn from each array, that sums to the given target number.

Assumptions

The two given arrays are not null and have length of at least 1
Examples

A = {3, 1, 5}, B = {2, 8}, target = 7, return true(pick 5 from A and pick 2 from B)

A = {1, 3, 5}, B = {2, 8}, target = 6, return false */

//Method 1
public class Solution {
  public boolean existSum(int[] a, int[] b, int target) {
    if (a == null || b == null || a.length == 0 || b.length == 0) {
        return false;
    }
    Set<Integer> set = new HashSet<>();
    for (int num : a) {
        set.add(num);
    }
    for (int num : b) {
        if (set.contains(target - num)) {
            return true;
        }
    }
    return false;
  }
}
//time: O(n), space: O(n)


//Method 2
public class Solution {
  public boolean existSum(int[] nums1, int[] nums2, int target) {
    if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
      return false;
    }
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int start = 0, end = nums2.length - 1;

    while (start < nums1.length && end >= 0) {
      if (nums1[start] + nums2[end] == target) {
        return true;
      } else if (nums1[start] + nums2[end] < target) {
        start++;
      } else {
        end--;
      }
    }
    return false;
  }
}
//time: O(nlogn), space: O(1)