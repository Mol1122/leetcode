/* Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s. 

 

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined. */

class Solution {
    public int longestOnes(int[] A, int k) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int count = 0;
        int j = 0, max = 0;
        
        for (int i = 0; i < A.length; i++) {
            while (j < A.length && (A[j] == 1 || A[j] == 0 && count < k)) {
                if (A[j] == 0) {
                    count++;
                }
                j++;
            }
            max = Math.max(max, j - i);
            if (A[i] == 0) {
                count--;
            }
        }
        return max;
    }
}
//time: O(n), space: O(1)