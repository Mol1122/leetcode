/* Given a non-negative integer, you could swap two digits at most once to get the maximum valued 
number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap. */

class Solution {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] buckets = new int[10];
        
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i; //记录这个数字最大的index
        }
        for (int i = 0; i < digits.length; i++) {
            for (int j = 9; j > digits[i] - '0'; j--) {
                if (buckets[j] > i) { //保证在它右边
                    char temp = digits[buckets[j]];
                    digits[buckets[j]] = digits[i];
                    digits[i] = temp;
                    return Integer.valueOf(new String(digits));
                }
            } 
        }
        return num;
    }
}

/* 算法：可以统计出每一位数字最后出现的位置, 然后从最高位开始, 对于每一位尝试寻找一个在它右边的最大的数, 若能找到, 直接交换即可
** 语法：Integer.toString(num)
** 难点：Integer.valueOf(new String(digits))
** time：O(n), sapce: O(1) */