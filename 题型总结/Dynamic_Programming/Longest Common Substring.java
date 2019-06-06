/* Find the longest common substring of two given strings.

Assumptions

The two given strings are not null
Examples

S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”  */

public class Solution {
  public String longestCommon(String A, String B) {
    if (A == null || B == null || A.length() == 0 || B.length() == 0) {
      return "";
    }
    int n = A.length();
    int m = B.length();
    int[][] f = new int[n + 1][m + 1];

    int max = 0;
    String result = "";
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          f[i][j] = f[i - 1][j - 1] + 1;
          if (f[i][j] > max) {
            max = f[i][j];
            result = A.substring(i - f[i][j], i);
          }
        }
      }
    }
    return result;
  }
}
//f[i][j] = longest common substring ending at the first i char of A and the first j char of B
//time: O(nm), space: O(nm)
