public class Solution {
  public boolean canMerge(String ss1, String ss2, String ss3) {
    if (ss1 == null || ss2 == null) {
        return false;
    }
    if (ss1.length() + ss2.length() != ss3.length()) {
        return false;
    }
    char[] s1 = ss1.toCharArray();
    char[] s2 = ss2.toCharArray();
    char[] s3 = ss3.toCharArray();
    boolean[][] f = new boolean[s1.length + 1][s2.length + 1];
    f[0][0] = true;

    for (int i = 0; i <= s1.length; i++) {
        f[i][0] = ss1.substring(0, i).equals(ss3.substring(0, i));
    }
    for (int j = 0; j <= s2.length; j++) {
        f[0][j] = ss2.substring(0, j).equals(ss3.substring(0, j));
    }
    for (int i = 1; i <= s1.length; i++) {
        for (int j = 1; j <= s2.length; j++) {
            f[i][j] = f[i - 1][j] && s1[i - 1] == s3[i + j - 1] ||
                      f[i][j - 1] && s2[j - 1] == s3[i + j - 1];
        }
    }
    return f[s1.length][s2.length];
  }
}
//time: O(n^2), space: O(n^2)
