/* Given two integers a and b, calculate a / b without using divide/mod operations.

Examples:

0 / 1 = 0

1 / 0 = Integer.MAX_VALUE

-1 / 0 = Integer.MAX_VALUE

11 / 2 = 5

-11 / 2 = -5

11 / -2 = -5

-11 / -2 = 5 */

class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isNegative = dividend > 0 && divisor < 0 ||
                             dividend < 0 && divisor > 0;
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        
        int result = 0;
        while (a >= b) {
            int shift = 0;
            while (a >= (b << shift)) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative ? -result : result;
    }
}

/* 算法：
碰到shift的题可以用十进制去理解. 相除转换成加法去理解
假设a = 425, b = 17
425 > 17
425 > 17 * 10 但是不大于17 * 100
425-170表示result至少有10个1
255 > 17 * 10所以result还是要加10
以此类推，看有多少个17相加能小于等于dividend

难点： 因为Integer.MIN_VALUE没有对应的正数，所以它的abs就是它自己，因此变为long进行处理 */
//time: O(logn), space: O(1)