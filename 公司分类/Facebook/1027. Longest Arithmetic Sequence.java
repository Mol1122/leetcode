/* Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, 
and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

 

Example 1:

Input: [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.
Example 2:

Input: [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].
Example 3:

Input: [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5]. */

class Solution {
    public int longestArithSeqLength(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[][] f = new int[A.length][A.length];
        int max = 0;
        
        for (int j = 0; j < A.length; j++) {
            for (int i = 0; i < j; i++) {
                f[i][j] = 2;
                int diff = A[j] - A[i];
                
                for (int k = 0; k < i; k++) {
                    if (A[i] - A[k] == diff) {
                        f[i][j] = Math.max(f[i][j], f[k][i] + 1);
                    }
                }
                max = Math.max(max, f[i][j]);
            }
        }
        return max;
    }
}
//算法：dp
//f[i][j] = max length that the last two numbers are A[i] and A[j]
//time: O(n^3), space: O(n^2)