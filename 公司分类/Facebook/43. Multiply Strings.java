/* Given two non-negative integers num1 and num2 represented as strings, return the product of 
num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088" */

class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return "";
        }
        int l1 = num1.length();
        int l2 = num2.length();
        int[] ans = new int[l1 + l2 + 1];
        
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                ans[i + j] += (num1.charAt(l1 - 1 - i) - '0') * (num2.charAt(l2 - 1 - j) - '0');
            }
        }
        for (int i = 0; i < l1 + l2; i++) {
            ans[i + 1] += ans[i] / 10;
            ans[i] = ans[i] % 10;
        }
        int i = l1 + l2;
        while (i >= 1 && ans[i] == 0) {
            i--;
        }
        String result = "";
        while (i >= 0) {
            result += ans[i--] + "";
        }
        return result;
    }
}
//time: O(n * m), space: O(n + m)