/* There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

 */

public class Solution {
  public int minCostII(int[][] costs) {
    if (costs == null || costs.length == 0) {
        return 0;
    }
    if (costs[0].length == 1) { //corner case
        return costs[0][0];
    }
    int n = costs.length;
    int k = costs[0].length;
    int[][] f = new int[n + 1][k];

    for (int i = 0; i < k; i++) {
        f[0][i] = 0;
    }
    for (int i = 1; i <= n; i++) {
        int min1 = -1, min2 = -1;
        for (int l = 0; l < k; l++) {
            if (min1 == -1 || f[i - 1][l] < f[i - 1][min1]) {
                min2 = min1;
                min1 = l;
            } else {
                if (min2 == -1 || f[i - 1][l] < f[i - 1][min2]) {
                    min2 = l;
                }
            }
        }
        for (int j = 0; j < k; j++) {
            f[i][j] = Integer.MAX_VALUE;
            if (j != min1) {
                f[i][j] = Math.min(f[i][j], f[i - 1][min1] + costs[i - 1][j]);
            } else {
                f[i][j] = Math.min(f[i][j], f[i - 1][min2] + costs[i - 1][j]);
            }
        }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < k; i++) {
        min = Math.min(min, f[n][i]);
    }
    return min;
  }
}
//time: O(nk), space: O(nk)
//难点：注意corner case