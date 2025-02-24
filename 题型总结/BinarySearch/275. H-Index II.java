/* Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper and citations is sorted in ascending order, return the researcher's h-index.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.

You must write an algorithm that runs in logarithmic time.

 

Example 1:

Input: citations = [0,1,3,5,6]
Output: 3
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had received 0, 1, 3, 5, 6 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
Example 2:

Input: citations = [1,2,100]
Output: 2 */

class Solution {
    public int hIndex(int[] nums) {
        if (nums != null && nums.length == 1 && nums[0] == 0) {
            return 0;
        }
        if (nums == null || nums.length == 1) {
            return 1;
        }
        
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums.length - mid > nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums.length - start <= nums[start]) {
            return nums.length - start;
        }
        if (nums.length - end <= nums[end]) {
            return nums.length - end;
        }
        
        return 0;
    }
}