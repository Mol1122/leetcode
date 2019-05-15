/* 
Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. Determine the fewest cuts needed for a palindrome partitioning of a given string.

Assumptions

The given string is not null
Examples

“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.

The minimum number of cuts needed is 3. */
public class Solution {
  public int minCuts(String s) {
      if (s == null || s.length() == 0) {
          return 0;
      }
      boolean[][] isPalin = getPalindrome(s);
      int[] f = new int[s.length() + 1];
      f[0] = 0;
    
      for (int i = 1; i <= s.length(); i++) {
          f[i] = Integer.MAX_VALUE;
          for (int j = 0; j < i; j++) { //starting index of the right substring
              if (isPalin[j][i - 1]) {
                  f[i] = Math.min(f[i], f[j] + 1);
              }
          }
      }
      return f[s.length()] - 1;
  }
  
  private boolean[][] getPalindrome(String s) {
      int n = s.length();
      boolean[][] isPalin = new boolean[n][n];
    
      for (int i = 0; i < n; i++) {
          isPalin[i][i] = true;
      }
      for (int i = 0; i < n - 1; i++) {
          isPalin[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
      }
      for (int i = n - 3; i >= 0; i--) {
          for (int j = i + 2; j < n; j++) {
              isPalin[i][j] = isPalin[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
          }
      }
      return isPalin;
  }
}
//f[i] = the min number palindromes that the first i chars can form
//time: O(n^2), space: O(n^2)