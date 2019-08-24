/* Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
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
        return dfs(s, 0, p, 0, memo, visited);
    }
    
    private boolean dfs(String s, int sIndex, String p, int pIndex, 
                        boolean[][] memo, boolean[][] visited) {
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }
        if (sIndex == s.length()) {
            return checkStar(p, pIndex);
        }
        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }
        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean isMatch = false;
        
        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            isMatch = dfs(s, sIndex, p, pIndex + 2, memo, visited) ||
                sameChar(sChar, pChar) && dfs(s, sIndex + 1, p, pIndex, memo, visited);
        } else {
            isMatch = sameChar(sChar, pChar) && 
                dfs(s, sIndex + 1, p, pIndex + 1, memo, visited);
        }
        
        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = isMatch;
        return isMatch;
    }
    
    private boolean sameChar(char s, char p) {
        return s == p || p == '.';
    }
    
    private boolean checkStar(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i += 2) {
            if (i + 1 >= p.length() || p.charAt(i + 1) != '*') {
                return false;
            }
        }
        return true;
    }
}
//time: O(n *m) , space: O(n * m)