class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> results = new ArrayList<>();
        int[] count = getLeftRightCount(s);
        dfs(s, 0, count[0], count[1], results);
        return results;
    }
    
    private void dfs(String s, int startIndex, int leftCount, int rightCount, List<String> results) {
        if (leftCount == 0 && rightCount == 0 && valid(s)) {
            results.add(s);
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (i > startIndex && s.charAt(i) == s.charAt(i - 1)) { //避免重复答案
                continue;
            }
            if (leftCount > 0 && s.charAt(i) == '(') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount - 1, rightCount, results);
            }
            if (rightCount > 0 && s.charAt(i) == ')') {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount, rightCount - 1, results);
            }
        }
    }
    
    private boolean valid(String s) {
        int[] count = getLeftRightCount(s);
        return count[0] == 0 && count[1] == 0;
    }
    
    private int[] getLeftRightCount(String s) {
        int[] count = new int[]{0, 0};
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count[0]++;
            } else if (c == ')') {
                if (count[0] > 0) {
                    count[0]--; //删除已经valid的计数，很好的技巧
                } else {
                    count[1]++;
                }
            }
        }
        return count;
    }
}

/* 有多少个combination一般都要用到dfs, 关键点在于利用leftCount和rightCount */