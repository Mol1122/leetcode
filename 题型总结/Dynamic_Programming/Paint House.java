/* There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers. */

public class Solution {
  public int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) {
        return 0;
    }
    int n = costs.length;
    int[][] f = new int[n + 1][3];
    f[0][0] = f[0][1] = f[0][2] = 0;

    for (int i = 1; i <= n; i++) {
        f[i][0] = Math.min(f[i - 1][1], f[i - 1][2]) + costs[i - 1][0];
        f[i][1] = Math.min(f[i - 1][0], f[i - 1][2]) + costs[i - 1][1];
        f[i][2] = Math.min(f[i - 1][0], f[i - 1][1]) + costs[i - 1][2];
    }
    return Math.min(f[n][0], Math.min(f[n][1], f[n][2]));
  }
}
//time: O(n), space: O(n)