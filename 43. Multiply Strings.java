class Solution {
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int[] ans = new int[l1 + l2 + 1];
        
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                ans[i + j] += (num1.charAt(l1 - 1 - i) - '0') * (num2.charAt(l2 - 1 - j) - '0'); //别写错*
            }
        }
        
        for (int i = 0; i < l1 + l2; i++) {
            ans[i + 1] += ans[i] / 10; //别漏+
            ans[i] %= 10;
        }
        String str = "";
        int i = l1 + l2;
        while (ans[i] == 0 && i >= 1) {
            i--;
        }
        while (i >= 0) {
            str += ans[i--]; //别漏i--
        }
        return str;
    }
}

/* 乘法要用到高精度计算里面的一次性进位
** 算法：第一步：ans[i+j] = a[i] * b[j] 
**      第二部：一次性进位 */