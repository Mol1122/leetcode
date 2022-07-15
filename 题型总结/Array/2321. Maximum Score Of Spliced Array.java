/* You are given two 0-indexed integer arrays nums1 and nums2, both of length n.

You can choose two integers left and right where 0 <= left <= right < n and swap the subarray nums1[left...right] with the subarray nums2[left...right].

For example, if nums1 = [1,2,3,4,5] and nums2 = [11,12,13,14,15] and you choose left = 1 and right = 2, nums1 becomes [1,12,13,4,5] and nums2 becomes [11,2,3,14,15].
You may choose to apply the mentioned operation once or not do anything.

The score of the arrays is the maximum of sum(nums1) and sum(nums2), where sum(arr) is the sum of all the elements in the array arr.

Return the maximum possible score.

A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).

 

Example 1:

Input: nums1 = [60,60,60], nums2 = [10,90,10]
Output: 210
Explanation: Choosing left = 1 and right = 1, we have nums1 = [60,90,60] and nums2 = [10,60,10].
The score is max(sum(nums1), sum(nums2)) = max(210, 80) = 210.
Example 2:

Input: nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
Output: 220
Explanation: Choosing left = 3, right = 4, we have nums1 = [20,40,20,40,20] and nums2 = [50,20,50,70,30].
The score is max(sum(nums1), sum(nums2)) = max(140, 220) = 220.
Example 3:

Input: nums1 = [7,11,13], nums2 = [1,1,1]
Output: 31
Explanation: We choose not to swap any subarray.
The score is max(sum(nums1), sum(nums2)) = max(31, 3) = 31. */

class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        int ans = 0, sum1 = 0, sum2 = 0;
        for (int num : nums1) {
            sum1 += num;
        }
        for (int num : nums2) {
            sum2 += num;
        }
        ans = Math.max(sum1, sum2); //no swaps
        
        int first = 0, second = 0, max1 = 0, max2 = 0;
        
        for (int i = 0; i < nums1.length; i++) {
            first += nums2[i] - nums1[i];
            second += nums1[i] - nums2[i];
            
            max1 = Math.max(max1, first);
            max2 = Math.max(max2, second);
            
            first = Math.max(0, first);
            second = Math.max(0, second);
        }
        ans = Math.max(ans, sum1 + max1);
        ans = Math.max(ans, sum2 + max2);
        return ans;
    }
}

/* We can either increase sum of nums1 or sum of num2
If we want to increase sum of nums1, we will select subarray from i to j with maximum sum of nums2[i] - nums1[i];
If we want to increase sum of nums2, we will select subarray from i to j with maximum sum of nums1[i] - nums2[i];

Time Complexity -> O(N)
Space Complexity -> O(1) */