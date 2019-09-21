/* Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4] */

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        //time: O(nlogn + mlogm), space: O(1)
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] temp = new int[nums1.length + nums2.length];
        
        int i = 0, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (index == 0 || nums1[i] != temp[index - 1]) {
                    temp[index++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOf(temp, index);
        
        //time: O(n + m), space: O(n)
        // if (nums1 == null || nums2 == null) {
        //     return new int[0];
        // }
        // Set<Integer> set = new HashSet<>();
        // for (int num : nums1) {
        //     set.add(num);
        // }
        // Set<Integer> resultHash = new HashSet<>();
        // for (int num : nums2) {
        //     if (set.contains(num)) {
        //         resultHash.add(num);
        //     }
        // }
        // int[] results = new int[resultHash.size()];
        // int index = 0;
        // for (int num : resultHash) {
        //     results[index++] = num;
        // }
        // return results;
    }
}

/* 算法：n, m   n < m               time                    space
    1. hashmap                      O(n + m) (O(n*l + m*l)假设n是小的那个， l是string长度)        O(n)  存短的进hashmap
    2. binary search                O(nlogm)        O(1) sort长的那个
    3.sort two list + merge         O(nlogn + mlogm)        O(1)
*/