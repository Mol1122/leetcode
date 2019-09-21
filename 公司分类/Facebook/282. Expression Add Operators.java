/* Given a string that contains only digits 0-9 and a target value, return all possibilities to add 
binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: [] */

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
//time: O(n^n), space: O(n)