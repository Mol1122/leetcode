/* Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two, if there exists an integer x such that n == 2x.

 

Example 1:

Input: n = 1
Output: true
Explanation: 20 = 1
Example 2:

Input: n = 16
Output: true
Explanation: 24 = 16
Example 3:

Input: n = 3
Output: false */

class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && countBits(n) == 1;
    }
    
    private int countBits(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1);
            n = (n >> 1);
        }
        return count;
    }
}
//time: O(#bits), space: O(1)