/* Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: [] */

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> memo = new HashMap<>();
        Set<String> dict = new HashSet<>();
        for (String str : wordDict) {
            dict.add(str);
        }
        return dfs(s, dict, memo);
    }
    
    private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        List<String> results = new ArrayList<>();
        
        if (s.length() == 0) {
            return results;  
        }
        if (dict.contains(s)) {
            results.add(s); //不能return,因为接下来还需要遍历
        }
        
        for (int i = 1; i < s.length(); i++) {
            String sub = s.substring(0, i);
            if (!dict.contains(sub)) {
                continue;
            }
            String suffix = s.substring(i); //从哪开始一定要注意
            List<String> segmentations = dfs(suffix, dict, memo);
            for (String segmentation : segmentations) {
                results.add(sub + " " + segmentation);
            }
        }
        memo.put(s, results);
        return results;
    }
}
/* 算法：记忆化搜索<-因为要找出所有法案，并且很多次遍历都有重复 */


class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> results = new ArrayList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return results;
        }
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add(word);
        }
        Map<String, List<String>> map = new HashMap<>();

        return dfs(s, dict, map);
    }

    private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> list = new ArrayList<>();
        if (s.equals("")) {
            return list;
        }
        if (dict.contains(s)) {
            list.add(s);
        }
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(0, i + 1);
            if (dict.contains(sub)) {
                List<String> nexts = dfs(s.substring(i + 1), dict, map);
                for (String next : nexts) {
                    list.add(sub + " " + next);
                }
            }
        }
        map.put(s, list);
        return list;
    }
}