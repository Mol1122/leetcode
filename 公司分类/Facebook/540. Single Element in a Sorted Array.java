/* You are given a sorted array consisting of only integers where every element appears 
exactly twice, except for one element which appears exactly once. Find this single element 
that appears only once.

 

Example 1:

Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: [3,3,7,7,10,11,11]
Output: 10 */

class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid % 2 != 0) {
                mid--;  
            } 
            if (nums[mid] != nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 2;
            }
           
        }
        return nums[start];
    }
}
//time: O(logn), space: O(1)