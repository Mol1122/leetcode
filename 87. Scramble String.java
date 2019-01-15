class Solution {
    Map<String, Boolean> hash = new HashMap<>();
    
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        } 
        if (hash.containsKey(s1 + "#" + s2)) {
            return hash.get(s1 + "#" + s2);
        }
        
        int n = s1.length();
        if (n == 1) {
            return s1.charAt(0) == s2.charAt(0);
        }
        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                isScramble(s1.substring(i, n), s2.substring(i, n)) ||
                isScramble(s1.substring(0, i), s2.substring(n - i, n)) && 
                isScramble(s1.substring(i, n), s2.substring(0, n - i))) {
                hash.put(s1 + "#" + s2, true);
                return true;
            }
        }
        hash.put(s1 + "#" + s2, false);
        return false;
    }
}

/* 算法：记忆化搜索
** T和S的长度不一样，那么肯定不能由S变换而来。如果T是S变换而来的，并且我们知道S最上层被分成S = S1S2, 那么一定有：
- T = T1T1, T1是由S1变换而来的， T2是由S2变换而来的
- T = T1T2, T1是由S2变换而来的， T2是由S1变换而来的
** 时间复杂度：O(n^3) 参数的组合个数：n^2 -> "s1#s2"的可能性是； dfs里的for循环：n */