/* Giving a string with number from 1-n in random order, but miss 1 number.Find that number.

Example
Example1

Input: n = 20 and str = 19201234567891011121314151618
Output: 17
Explanation:
19'20'1'2'3'4'5'6'7'8'9'10'11'12'13'14'15'16'18
Example2

Input: n = 6 and str = 56412
Output: 3
Explanation:
5'6'4'1'2
Notice
n <= 30
Data guarantees have only one solution */

public class Solution {
    /**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    int ans;
    public int findMissing2(int n, String str) {
        if (n < 1 || str == null || str.length() == 0) {
            return -1;
        }
        ans = -1;
        boolean[] found = new boolean[n + 1];
        dfs(str, 0, found, n);
        return ans;
    }
    
    private void dfs(String str, int startIndex, boolean[] found, int n) {
        if (ans != -1) {
            return;
        }
        if (startIndex == str.length()) {
            for (int i = 1; i <= n; i++) {
                if (found[i] == false) {
                    ans = i;
                    return;
                }
            }
            return;
        }
        
        if (str.charAt(startIndex) == '0') {
            return;
        }
        
        for (int i = 1; i <= 2 && startIndex + i <= str.length(); i++) {
            String sub = str.substring(startIndex, startIndex + i);
            int number = Integer.parseInt(sub);
            if (number >= 1 && number <= n && !found[number]) {
                found[number] = true;
                dfs(str, startIndex + i, found, n);
                found[number] = false;
            }
        }
    }
}