public class Solution {
  public int longest(String A, String B) {
    if (A == null || B == null || A.length() == 0 || B.length() == 0) {
        return 0;
    }
    int[][] f = new int[A.length() + 1][B.length() + 1];
    int max = 0;
    for (int i = 1; i <= A.length(); i++) {
        for (int j = 1; j <= B.length(); j++) {
            f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            if (A.charAt(i - 1) == B.charAt(j - 1)) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
            }
            max = Math.max(max, f[i][j]);
        }
    }
    return f[A.length()][B.length()];
  }
}
//f[i][j] = the longest common sequence with first i chars of A, and first j chars of B
//time: O(n^2), space: O(n^2)