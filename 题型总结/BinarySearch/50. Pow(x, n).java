/* Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1] */

class Solution {
    public double myPow(double x, int n) {
        if (x == 0 && n == 0) {
            return Integer.MIN_VALUE;
        } else if (x == 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        if (n > 0) {
            return helper(x, n);
        } else {
            return 1.0 / helper(x, n);
        }
    }
    
    private double helper(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double half = helper(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
//time: O(logn), space: O(logn)