/* Calculate the a^n \% ba 
n
 %b where a, b and n are all 32bit non-negative integers.

 Example 1:

Input:

a = 3
b = 7
n = 5
Output:

5
Explanation:

3 ^ 5 % 7 = 5

Example 2:

Input:

a = 3
b = 1
n = 0
Output:

5
Explanation:

3 ^ 0 % 1 = 0

Challenge
O(logn)O(logn) time complexity */

public class Solution {
    /**
     * @param a: A 32bit integer
     * @param b: A 32bit integer
     * @param n: A 32bit integer
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        long ans = 1, temp = a;
        
        while (n != 0) {
            if (n % 2 == 1) {
                ans = (ans * temp) % b;
            }
            temp = (temp * temp) % b;
            n /= 2;
        }
        return (int)ans % b;
    }
}