class Solution {
    //双指针
//     public boolean isMatch(String s, String p) {
//         int i = 0, j = 0, starIndex = -1, iIndex = 0;
        
//         while (i < s.length()) {
//         	if ((j < p.length() && p.charAt(j) == '?') || (j < p.length() && s.charAt(i) == p.charAt(j))) {
//         		i++;
//         		j++;
//         	} else if (j < p.length() && p.charAt(j) == '*') {
//         		starIndex = j;
//         		iIndex = i;
//         		j++;
//         	} else if (starIndex != -1) {
//         		j = starIndex + 1;
//         		i = iIndex + 1;
//         		iIndex++;
//         	} else {
//         		return false;
//         	}
//         }
//         while (j < p.length() && p.charAt(j) == '*') {
//         	j++;
//         }
//         return j == p.length();
//     }
    
    //记忆化搜索 + DFS: 时间复杂度：(方案数 = mn) * (构造一个方案的时间 = 2^n) 
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        
        return dfs(s, 0, p, 0, memo, visited);
    }
    
    private boolean dfs(String s, int sIndex, String p, int pIndex, boolean[][] memo, boolean[][] visited) {
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }
        if (sIndex == s.length()) {
            return allStar(p, pIndex);
        }
        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }
        
        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean match = false;
        
        if (pChar == '*') { //一分为二，二分为四，所以时间复杂度是2^n
            match = dfs(s, sIndex, p, pIndex + 1, memo, visited) ||  //match 0 个
                    dfs(s, sIndex + 1, p, pIndex, memo, visited); //match 1个或多个，多个情况给下一个recursion处理
        } else {
            match = isMatch(sChar, pChar) && dfs(s, sIndex + 1, p, pIndex + 1, memo, visited);
        }
        
        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = match;
        return match;
    }
    
    private boolean isMatch(char sChar, char pChar) {
        return sChar == pChar || pChar == '?';
    }
    
    private boolean allStar(String str, int index) {
        for (int i = index; i < str.length(); i++) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}

/* 算法：用双指针去解决最简单。i遍历s, j遍历p
*  时间复杂度：O(n) */