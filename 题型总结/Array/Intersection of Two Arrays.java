/* Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order. */

public class Solution {
  public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
      return new int[0];
    }
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    List<Integer> temp = new ArrayList<>();

    int i = 0, j = 0, index = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        temp.add(nums1[i]);
        i++;
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else {j++;}
    }
    int[] results = new int[temp.size()];
    for (int num : temp) {
      results[index++] = num;
    }
    return results;
  }
}
//time: O(nlogn + mlogm + n + m), space: O(n)