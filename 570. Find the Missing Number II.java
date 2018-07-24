public class Solution {
    /**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    int missing = -1;
    public int findMissing2(int n, String str) {
        if (str == null || str.length() == 0 || n < 1) {
            return -1;
        }
        
        dfs(str, 0, n, new boolean[n + 1], 0);
        return missing;
    }
    
    private void dfs(String str, int startIndex, int n, boolean[] visited, int count) {
        if (startIndex == str.length() && count == n - 1) {
            for (int i = 1; i <= n; i++) {
                if (visited[i] == false) {
                    missing = i;
                    return;
                }
            }
            return;
        }
        
        for (int i = 1; i <= 2 && startIndex + i - 1 < str.length(); i++) {
            int num = Integer.parseInt(str.substring(startIndex, startIndex + i));
            if (str.charAt(startIndex) == 0 || num < 1 || num > n) {
                continue;
            }
            if (visited[num]) {
                continue;
            }
            
            visited[num] = true;
            dfs(str, startIndex + i, n, visited, count + 1);
            visited[num] = false;
        }
        
    }
}
/* 算法：因为要尝试，拿一个数或者拿两个数。所以想到了标准的dfs搜索
** 难点：1. 如果visited[num] = true, 说明之前已经存在了不需要接着遍历了
         2. substring(start, end), end可以等于str.length(), 需要特别注意 */