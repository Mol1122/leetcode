/* Given two strings where first string is a normal string and second string may contain wild card characters. Write a function that returns true if the two strings match. The following are allowed wildcard characters in first string. 
* --> Matches with 0 or more instances of any character or set of characters.
? --> Matches with any one character.

Assumptions:

The two strings are both not null.
Assume there is no more than one '*' adjacent to each other.
Examples:

input = "abc", pattern = "?*", return true. */

public class Solution {
  public boolean match(String s, String p) {
    if (s == null || p == null) {
        return false;
    }
    int n = s.length();
    int m = p.length();
    boolean[][] memo = new boolean[n][m];
    boolean[][] visited = new boolean[n][m];
    return dfs(s, 0, p, 0, visited, memo);
  }

  private boolean dfs(String s, int sIndex, String p, int pIndex, 
                      boolean[][] visited, boolean[][] memo) {
    if (sIndex == s.length()) {
        if (isAllStars(p, pIndex)) {
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
    boolean isMatch = false;

    if (pChar == '*') {
        isMatch = dfs(s, sIndex, p, pIndex + 1, visited, memo) || 
                  dfs(s, sIndex + 1, p, pIndex, visited, memo);
    } else {
        isMatch = match(s.charAt(sIndex), p.charAt(pIndex)) && 
                    dfs(s, sIndex + 1, p, pIndex + 1, visited, memo);
    }
    visited[sIndex][pIndex] = true;
    memo[sIndex][pIndex] = isMatch;
    return isMatch;
  }

  private boolean match(char s, char p) {
    return s == p || p == '?';
  }

  private boolean isAllStars(String p, int index) {
    for (int i = index; i < p.length(); i++) {
        if (p.charAt(i) != '*') {
            return false;
        }
    }
    return true;
  }
}
//time: O(2^n), space: O(nm + n)