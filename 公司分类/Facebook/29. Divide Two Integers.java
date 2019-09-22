/* Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2 */

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
            a -= (b << (shift - 1));
            result += (1 << (shift - 1));
        }
        return isNegative ? -result : result;
    }
}
//time: O(logn), space: O(1)