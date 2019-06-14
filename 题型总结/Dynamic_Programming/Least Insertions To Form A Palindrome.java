/* Insert the least number of characters to a string in order to make the new string a palindrome. Return the least number of characters should be inserted.

Assumptions:

The given string is not null. */

public class Solution {
  public int leastInsertion(String s) {
    if (s == null || s.length() <= 1) {
        return 0;
    }
    char[] sc = s.toCharArray();
    int[][] f = new int[sc.length][sc.length];

    for (int i = sc.length - 2; i >= 0; i--) {
        for (int j = i + 1; j < sc.length; j++) {
            if (sc[i] == sc[j]) {
                f[i][j] = f[i + 1][j - 1];
            } else {
                f[i][j] = Math.min(f[i + 1][j], f[i][j - 1]) + 1;
            }
        }    
    }
    return f[0][sc.length - 1];
  }
}
//time: O(n^2), space: O(n^2)