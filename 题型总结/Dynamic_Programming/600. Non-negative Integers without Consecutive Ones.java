/* Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.

Example 1:
Input: 5
Output: 5
Explanation: 
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.  */

class Solution {
    public int findIntegers(int num) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
        int n = sb.length();
        
        int[] a = new int[n]; //the number of non-consecutive-1 solutions with the i-th significant bit being 0;
        int[] b = new int[n]; //the number of non-consecutive-1 solutions with the i-th significant bit being 1;
        a[0] = b[0] = 1;
        for (int i = 1; i < n; i++) {
            a[i] = a[i - 1] + b[i - 1];
            b[i] = a[i - 1];
        }
        int res = a[n - 1] + b[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') {
                break;
            }
            if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') {
                res -= b[i];
            }
        }
        return res;
    }
}
/**算法：https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
        第二部分，prefix的结尾是10, res中是不可能有答案且比number大的，因为11 invalid
                             11, 不可能存在答案在res中
                             01， res中是不可能有答案且比number大的，因为01 没有比01更大的(02)
                             00, 01是有可能在res中，并且比number大的，所以要用res减掉01的个数
        when we substract the solutions which is larger than num, we iterate from most significant bit to the least significant bit
        
        time: O(n), space: O(n)
*/


//正着写，更加容易理解
class Solution {
    public int findIntegers(int num) {
        String binary = Integer.toBinaryString(num);
        int[] zero = new int[binary.length()]; 
        // zero[i] stores binary sequences with lengths <= i + 1
        // ending with 0s
        // that have no consecutive ones
        int[] one = new int[binary.length()];
        // one[i] stores binary sequences with lengths <= i + 1
        // ending with 1s
        // that have no consecutive ones
        
        // base case
        zero[0] = 1;
        one[0] = 1;
        // recurrence relation
        for (int i = 1; i < binary.length(); ++i) {
            zero[i] = zero[i - 1] + one[i - 1];
            one[i] = zero[i - 1];
        }
        
        int count = zero[binary.length() - 1] + one[binary.length() - 1];
        for (int i = 0; i < binary.length() - 1; ++i) {
            if (binary.charAt(i) == '1' && binary.charAt(i + 1) == '1') {
                break;
            }
            if (binary.charAt(i) == '0' && binary.charAt(i + 1) == '0') {
                count -= one[binary.length() - i - 2];
            }
        }
        return count;
    }
}