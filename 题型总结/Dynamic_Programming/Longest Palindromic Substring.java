public class Solution {
  public String longestPalindrome(String s) {
    if (s == null || s.length() == 0) {
        return "";
    }
    int n = s.length();
    boolean[][] f = new boolean[n][n];

    for (int i = 0; i < n; i++) {
        f[i][i] = true;
    }
    int longest = 1, startIndex = 0;
    for (int i = 0; i < n - 1; i++) {
        f[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        if (f[i][i + 1]) {
            longest = 2;
            startIndex = i;
        }
    }
    for (int i = n - 3; i >= 0; i--) {
        for (int j = i + 2; j < n; j++) {
            f[i][j] = f[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            if (f[i][j] && j - i + 1 > longest) {
                longest = j - i + 1;
                startIndex = i;
            }
        }
    }
    return s.substring(startIndex, startIndex + longest);
  }
}
//time: O(n^2), space: O(n^2)