/* There is a wooden stick with length L >= 1, we need to cut it into pieces, 
where the cutting positions are defined in an int array A. The positions are guaranteed 
to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length 
of the stick segment being cut. Determine the minimum total cost to cut the stick into the defined pieces.

Examples

L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7) */

public class Solution {
  public int minCost(int[] cuts, int length) {
    if (cuts == null || cuts.length == 0) {
        return 0;
    }
    int[] helper = new int[cuts.length + 2];
    helper[0] = 0;
    for (int i = 0; i < cuts.length; i++) {
        helper[i + 1] = cuts[i];
    }
    helper[helper.length - 1] = length;
    int n = helper.length;
    int[][] f = new int[n][n];

    for (int i = n - 1; i >= 0; i--) {
        for (int j = i + 1; j < n; j++) {
            if (j == i + 1) {
                f[i][j] = 0;
            } else {
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j]);
                }
                f[i][j] += helper[j] - helper[i];
            }
        }
    }
    return f[0][helper.length - 1];
  }
}
//f[i][j] = min cost of the partition[i, j]
//time: O(n^3), space: O(n^2)
//难点：从下往上，从左往右计算

//空间优化成 O(n)
public class Solution {
  public int longest(String A, String B) {
    if (A == null || B == null || A.length() == 0 || B.length() == 0) {
        return 0;
    }
    int n = A.length();
    int m = B.length();
    int[][] f = new int[2][m + 1];
    
    int max = 0;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            f[i%2][j] = Math.max(f[(i - 1)%2][j], f[i%2][j - 1]);
            if (A.charAt(i - 1) == B.charAt(j - 1)) {
                f[i%2][j] = Math.max(f[i%2][j], f[(i - 1)%2][j - 1] + 1);
            }
            max = Math.max(max, f[i%2][j]);
        }
    }
    return max;
  }
}