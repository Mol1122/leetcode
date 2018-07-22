class Solution {
    public boolean isMatch(String s, String p) {
        //记忆化搜索 + DFS
        if (s == null || p == null) {
            return false;
        }
        boolean[][] visited = new boolean[s.length()][p.length()];
        boolean[][] memo = new boolean[s.length()][p.length()];
        
        return dfs(s, 0, p, 0, memo, visited);
    }
    
    private boolean dfs(String s, int sIndex, String p, int pIndex, boolean[][] memo, boolean[][] visited) {
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }
        if (sIndex == s.length()) {
            return starCheck(p, pIndex);
        }
        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }
        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean match = false;
        
        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            match = dfs(s, sIndex, p, pIndex + 2, memo, visited) ||
                (charMatch(sChar, pChar) && dfs(s, sIndex + 1, p, pIndex, memo, visited));
        } else {
            match = charMatch(sChar, pChar) && dfs(s, sIndex + 1, p, pIndex + 1, memo, visited);
        }
        
        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = match;
        return match;
    }
    
    private boolean charMatch(char sChar, char pChar) {
        return sChar == pChar || pChar == '.';
    }
    
    private boolean starCheck(String p, int index) {
        for (int i = index; i < p.length(); i += 2) {
            if (i + 1 >= p.length() || p.charAt(i + 1) != '*') {
                return false;
            }
        }
        return true;
    }
}