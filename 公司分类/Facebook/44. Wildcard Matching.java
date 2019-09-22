/* Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false */

class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int n = s.length();
        int m = p.length();
        boolean[][] memo = new boolean[n][m];
        boolean[][] visited = new boolean[n][m];
        
        return dfs(s, 0, p, 0, memo, visited); //s从0开始，p从0开始，是否能match
    }
    
    private boolean dfs(String s, int sIndex, String p, int pIndex, boolean[][] memo, boolean[][] visited) {
        if (sIndex == s.length()) {
            return allStars(p, pIndex);
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
            isMatch = dfs(s, sIndex, p, pIndex + 1, memo, visited) ||
                      dfs(s, sIndex + 1, p, pIndex, memo, visited);
        } else {
            isMatch = equals(sChar, pChar) && dfs(s, sIndex + 1, p, pIndex + 1, memo, visited);
        }
        memo[sIndex][pIndex] = isMatch;
        visited[sIndex][pIndex] = true;
        return isMatch;
    }
    
    private boolean equals(char s, char p) {
        if (p == '?' || s == p) {
            return true;
        }
        return false;
    }
    
    private boolean allStars(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}
//time: O(n * m), space: O(n * m)