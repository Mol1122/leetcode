public class Solution {
  public int editDistance(String one, String two) {
      if (one == null || two == null) {
          return -1;
      }
      int[][] f = new int[one.length() + 1][two.length() + 1];
      for (int i = 0; i <= one.length(); i++) {
          f[i][0] = i;
      }
      for (int j = 0; j <= two.length(); j++) {
          f[0][j] = j;
      }
      
      for (int i = 1; i <= one.length(); i++) {
          for (int j = 1; j <= two.length(); j++) {
              if (one.charAt(i - 1) == two.charAt(j - 1)) {
                  f[i][j] = f[i - 1][j - 1];
              } else {
                  f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
              }
          }
      }
      return f[one.length()][two.length()];
  }
}
// f[i][j] = the min edit tht that the first i in s1 can transform to first j in s2
// xxxxxx|a b
// xxxxxx|b
//replace: 1 + f[i - 1][j - 1]
//delete: 1 + f[i - 1][j]
//insert: 1 + f[i][j - 1]

//time: O(n*m), space: O(n*m)