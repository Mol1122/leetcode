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