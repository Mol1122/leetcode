class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> results = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return results;
        }
        dfs(num, 0, target, "", 0, 0, results);
        return results;
    }
    
    private void dfs(String num, int startIndex, int target, String str, long sum, long lastF, List<String> results) {
        if (startIndex == num.length()) {
            if (sum == target) {
                results.add(str);
            }
            return;
        }
        // for (String s : results) {
        //     System.out.println(s);
        // }
        
        for (int i = startIndex; i < num.length(); i++) {
            String sub = num.substring(startIndex, i + 1);
            
            long x = Long.parseLong(sub);
            
            if (startIndex == 0) {
                dfs(num, i + 1, target, "" + x, x, x, results);
            } else {
                dfs(num, i + 1, target, str + "*" + x, sum - lastF + lastF * x, lastF * x, results);
                dfs(num, i + 1, target, str + "+" + x, sum + x, x, results);
                dfs(num, i + 1, target, str + "-" + x, sum - x, -x, results);
            }
            if (x == 0) {
                break; //以0开头的数只能取一次
            }
        }
    }
}

/* 算法：字符串的切割 = combination, 用combination的通用模版就好
** 难点：第一个字符，和前缀为0的字符需要单独判断 */