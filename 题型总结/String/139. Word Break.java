/* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false */

//动态规划
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Set<String> dict = new HashSet<>();
        for (String str : wordDict) {
            dict.add(str);
        }
        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        
        //左大段右小段思想
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) { //遍历左大段的长度
                if (f[j] && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
//f[i] = 前i个char是否能由dict组成
//time: O(n^3), space: O(n)

//记忆化搜索
class Solution {
    public boolean wordBreak(String s, List<String> worddict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Set<String> dict = new HashSet<>();
        for (String str : worddict) {
            dict.add(str);
        }
        int maxLength = getMax(dict);
        boolean[] canSegment = new boolean[s.length() + 1];
        canSegment[0] = true;
        
        for (int i = 1; i <= s.length(); i++) { //前i个字符串能否匹配上
            canSegment[i] = false;
            for (int lastLength = 1; lastLength <= maxLength && lastLength <= i; lastLength++) {
                if (canSegment[i - lastLength] == false) {
                    continue;
                }
                String temp = s.substring(i - lastLength, i);
                if (dict.contains(temp)) {
                    canSegment[i] = true;
                }
            }
        }
        return canSegment[s.length()];
    }
    
    private int getMax(Set<String> dict) {
        int max = Integer.MIN_VALUE;
        for (String s : dict) {
            max = Math.max(max, s.length());
        }
        return max;
    }
}
/* 算法：不能直接用搜索来做，时间复杂度太高了，会memory limit exceed. 动态规划 */
//time: O(n^3), space: O(n)