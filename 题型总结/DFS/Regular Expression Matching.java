/* Implement regular expression matching with support for '.' and '*'. '.' Matches any single character. '*' Matches zero or more of the preceding element. The matching should cover the entire input string (not partial).

Example

isMatch("aa","a") → false

isMatch("aa","aa") → true

isMatch("aaa","aa") → false

isMatch("aa", "a*") → true

isMatch("aa", ".*") → true

isMatch("ab", ".*") → true

isMatch("aab", "c*a*b") → true */

public class Solution {
  public boolean isMatch(String s, String p) {
    if (s == null || p == null) {
        return false;
    }
    int n = s.length();
    int m = p.length();
    boolean[][] visited = new boolean[n][m];
    boolean[][] memo = new boolean[n][m];

    return dfs(s, 0, p, 0, visited, memo);
  }

  private boolean dfs(String s, int sIndex, String p, int pIndex,
                      boolean[][] visited, boolean[][] memo) {
    if (sIndex == s.length()) {
        if (isValid(p, pIndex)) {
            return true;
        }
        return false;
    }
    if (pIndex == p.length()) {
        return sIndex == s.length();
    }
    if (visited[sIndex][pIndex]) {
        return memo[sIndex][pIndex];
    }
    char sChar = s.charAt(sIndex);
    char pChar = p.charAt(pIndex);
    boolean isMatched = false;

    if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
        isMatched = dfs(s, sIndex, p, pIndex + 2, visited, memo) ||
                  match(sChar, pChar) && dfs(s, sIndex + 1, p, pIndex, visited, memo);
    } else {
        isMatched = match(s.charAt(sIndex), p.charAt(pIndex)) &&
                  dfs(s, sIndex + 1, p, pIndex + 1, visited, memo);
    }
    visited[sIndex][pIndex] = true;
    memo[sIndex][pIndex] = isMatched;
    return isMatched;
  }

  private boolean match(char s, char p) {
    return s == p || p == '.';
  }

  private boolean isValid(String p, int index) {
    for (int i = index; i < p.length(); i += 2) {
        if (i + 1 >= p.length() || p.charAt(i + 1) != '*') {
            return false;
        }
    }
    return true;
  }
}
//time: O(2^n), space: O(nm + n)