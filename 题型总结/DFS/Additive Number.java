/* Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, 
each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8. */

public class Solution {
  public boolean isAdditiveNumber(String num) {
    if (num == null || num.length() < 3) {
        return false;
    }
    int n = num.length();
    for (int i = 1; i <= n / 2; i++) { //第一个数字的长度不能超过一半
        for (int j = 1; Math.max(i, j) <= n - i - j; j++) { //第二个数字最长不能比和长
            //从第二个数字往后一个个找，看是否能走到头
            if (isValid(i, j, num)) {
                return true;
            }
        }
    }
    return false;
  }

  private boolean isValid(int i, int j, String num) {
    if (num.charAt(0) == '0' && i > 0) {
        return false;
    }
    if (num.charAt(i) == '0' && j > 0) {
        return false;
    }
    String sum = "";
    long x1 = Long.parseLong(num.substring(0, i));
    long x2 = Long.parseLong(num.substring(i, i + j));
    //从第三个数开始循环找
    for (int start = i + j; start < num.length(); start += sum.length()) {
        x2 = x1 + x2;
        x1 = x2 - x1;
        sum = x2 + "";
        if (!num.startsWith(sum, start)) {
            return false;
        }
    }
    return true;
  }
}
/* 算法: 因为当确定前两个数字后，第三个数字往后就可以一步步退出来。所以我们只需要遍历前两个数字的组合。
** 难点：i, j表示的是num1, num2的长度，第三个数字的长度不能小于num1, num2中间大的那个 
** 时间复杂度：O(n^3)*/