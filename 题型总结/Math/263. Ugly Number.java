/* Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example 1:

Input: 6
Output: true
Explanation: 6 = 2 × 3
Example 2:

Input: 8
Output: true
Explanation: 8 = 2 × 2 × 2
Example 3:

Input: 14
Output: false 
Explanation: 14 is not ugly since it includes another prime factor 7. */

class Solution {
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        while (num >= 2 && num % 2 == 0) {
            num /= 2;
        }
        while (num >= 3 && num % 3 == 0) {
            num /= 3;
        }
        while (num >= 5 && num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }
}

/* 算法：数学题
** 难点：判断是否能整除的时候用%，但是除的时候用/ */