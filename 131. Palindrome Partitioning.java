class Solution {
    //时间复杂度非常高，可以优化isPalindrome
//     public List<List<String>> partition(String s) {
//         List<List<String>> results = new ArrayList<>();
//         if (s == null || s.length() == 0) {
//             return results;
//         }
//         dfs(s, 0, new ArrayList<>(), results);
//         return results;
//     }
    
//     private void dfs(String s, int startIndex, List<String> partition, List<List<String>> results) {
//         if (startIndex == s.length()) {
//             results.add(new ArrayList<>(partition));
//         }
        
//         for (int i = startIndex; i < s.length(); i++) {
//             String str = s.substring(startIndex, i + 1);
//             if (!isPalindrome(str)) {
//                 continue;
//             }
//             partition.add(str);
//             dfs(s, i + 1, partition, results);
//             partition.remove(partition.size() - 1);
//         }
//     }
    
//     private boolean isPalindrome(String str) {
//         for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
//             if (str.charAt(i) != str.charAt(j)) {
//                 return false;
//             }
//         }
//         return true;
//     }
    
    boolean[][] isPalindrome;
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }
        getPalindrome(s);
        dfs(s, 0, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(String s, int startIndex, List<String> partition, List<List<String>> results) {
        if (startIndex == s.length()) {
            results.add(new ArrayList<>(partition));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (!isPalindrome[startIndex][i]) {
                continue;
            }
            String sub = s.substring(startIndex, i + 1);
            partition.add(sub);
            dfs(s, i + 1, partition, results);
            partition.remove(partition.size() - 1);
        }
    }
    
    private void getPalindrome(String str) {
        int n = str.length();
        isPalindrome = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = str.charAt(i) == str.charAt(i + 1);
        }
        
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && str.charAt(i) == str.charAt(j);
            }
        }
    }
}

/* 算法：用动态规划去求palindrome效率比较高
** 难点：必须要有72-74,因为第三个for loop只能处理至少三个char的string
** 时间复杂度：O(2^n * n) */