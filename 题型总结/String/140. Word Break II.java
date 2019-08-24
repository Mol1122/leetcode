/* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[] */

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }
        Set<String> dict = new HashSet<>();
        for (String str : wordDict) {
            dict.add(str);
        }
        Map<String, List<String>> memo = new HashMap<>();
        return dfs(s, dict, memo);
    }
    
    private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        List<String> list = new ArrayList<>();
        if (s.length() == 0) {
            return list;
        }
        if (dict.contains(s)) {
            list.add(s);
        }
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (!dict.contains(sub)) {
                continue;
            }
            String suffix = s.substring(i);
            List<String> restList = dfs(suffix, dict, memo);
            for (String rest : restList) {
                list.add(sub + " " + rest);
            }
        }
        memo.put(s, list);
        return list;
    }
}
//算法：记忆化搜索
//time: O(n ^ 2), space: O(n). 