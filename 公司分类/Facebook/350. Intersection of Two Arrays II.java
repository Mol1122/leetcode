/* Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that 
you cannot load all elements into the memory at once? */

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
//         if (nums1 == null || nums2 == null) {
//             return new int[0];
//         }
//         Arrays.sort(nums1);
//         Arrays.sort(nums2);
//         List<Integer> temp = new ArrayList<>();
        
//         int i = 0, j = 0, index = 0;
//         while (i < nums1.length && j < nums2.length) {
//             if (nums1[i] == nums2[j]) {
//                 temp.add(nums1[i]);
//                 i++;
//                 j++;
//             } else if (nums1[i] < nums2[j]) {
//                 i++;
//             } else {
//                 j++;
//             }
//         }
//         int[] result = new int[temp.size()];
//         for (i = 0; i < result.length; i++) {
//             result[i] = temp.get(i);
//         }
//         return result;
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums1.length; ++i) {
            map.putIfAbsent(nums1[i], 0);
            map.put(nums1[i], map.get(nums1[i]) + 1);
        }

        List<Integer> results = new ArrayList<Integer>();

        for (int i = 0; i < nums2.length; ++i)
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                results.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1); 
            }

        int result[] = new int[results.size()];
        for(int i = 0; i < results.size(); ++i)
            result[i] = results.get(i);
        Arrays.sort(result);
        return result;
    }
}
/* 算法：这题不能用sort + binary search. 因为重复的东西你不能删掉再二分 */