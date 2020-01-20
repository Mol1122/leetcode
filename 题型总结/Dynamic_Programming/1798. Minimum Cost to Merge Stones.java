/* There are N piles of stones arranged in a row. The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is 
equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.

Example
Example 1:

Input: stones = [3,2,4,1], K = 2
Output: 20
Explanation: 
We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.
Example 1:

Input: stones = [3,2,4,1], K = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
Notice
1 <= stones.length <= 30
2 <= K <= 30
1 <= stones[i] <= 100
 */

public class Solution {
    public int mergeStones(int[] stones, int K) {
        if (stones.length <= 1) {
            return 0;
        }
        if ((stones.length - 1) % (K - 1) != 0) {
            return -1;
        }
        
        int n = stones.length;
        
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }
        
        int[][] f = new int[n][n]; //（合并下标从i~j的区间剩余1堆）的总开销
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k += (K - 1)) { //increment by K - 1才有可能(i, k)包含3栈
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j]);
                }
                if ((j - i + 1 - K) % (K - 1) == 0) {
                    f[i][j] += sum[j + 1] - sum[i];
                }
            }
        }
        
        return f[0][n - 1];
    }
}
//难点：如果区间i，j的长度为len，只有满足（len-1）%（k-1）=0的区间才有可能合并为长度为1的区间
//time: O(n^2), space: O(n^2)