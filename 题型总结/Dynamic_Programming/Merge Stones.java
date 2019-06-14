/* We have a list of piles of stones, each pile of stones has a certain weight, represented by an array of integers. In each move, we can merge two adjacent piles into one larger pile, the cost is the sum of the weights of the two piles. We merge the piles of stones until we have only one pile left. Determine the minimum total cost.

Assumptions

stones is not null and is length of at least 1
Examples

{4, 3, 3, 4}, the minimum cost is 28

merge first 4 and first 3, cost 7

merge second 3 and second 4, cost 7

merge two 7s, cost 14

total cost = 7 + 7 + 14 = 28 */

public class Solution {
  public int minCost(int[] stones) {
    if (stones == null || stones.length < 1) {
        return 0;
    }
    int n = stones.length;
    int[][] f = new int[n][n];
    int[] sums = new int[n];
    sums[0] = stones[0];

    for (int i = 1; i < n; i++) {
        sums[i] = sums[i - 1] + stones[i];
    }
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i + 1; j < n; j++) {
            f[i][j] = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + sums[j] - sums[i] + stones[i]);
            }
        }
    }
    return f[0][n - 1];
  }
}
//算法：区间型dp,思考最后一步，其实是个bottom to top的问题 
//time: O(n^3), space: O(2)