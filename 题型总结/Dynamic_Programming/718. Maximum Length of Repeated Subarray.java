/* Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:

Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1]. */

class Solution {
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null) {
            return 0;
        }
        int n = A.length;
        int m = B.length;
        int[][] f = new int[n + 1][m + 1]; //max length of repeated subarray ending at ith in A and jth in B
        int max = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    max = Math.max(max, f[i][j]);
                }
            }
        }
        return max;
    }
}
//time: O(n * m), space: O(n * m)