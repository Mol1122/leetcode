class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, target, 0, "", 0, 0, ans);
        return ans;
    }
    
    private void dfs(String num, int target, int start, String str, long sum, long lastF, List<String> ans) {
        if (start == num.length()) {
            if (target == sum) {
                ans.add(str);
            }
            return;
        }
        for (int i = start; i < num.length(); i++) {
            long x = Long.parseLong(num.substring(start, i + 1));
            if (start == 0) {
                dfs(num, target, i + 1, "" + x, x, x, ans);
            } else {
                dfs(num, target, i + 1, str + "*" + x, sum - lastF + x * lastF, x * lastF, ans);
                dfs(num, target, i + 1, str + "+" + x, sum + x, x, ans);
                dfs(num, target, i + 1, str + "-" + x, sum - x, -x, ans);
            }
            if (x == 0) {
                break;
            }
        }
    }
}

/* 算法：枚举型dfs
** 难点：long sum, long lastF */