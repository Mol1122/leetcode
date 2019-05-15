/* There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:

Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1 */ 

class Solution {
    public int numWays(int n, int k) {
        int[] f = {0, k, k * k, 0};
        
        if (n <= 2) {
            return f[n];
        }
        if (k == 1) {
            return 0;
        }
        for (int i = 2; i < n; i++) {
            f[3] = f[2] * (k - 1) + f[1] * (k - 1);
            f[1] = f[2];
            f[2] = f[3];
        }
        return f[3];
    }
}
/* 算法：题目要求连续三个fence不能同样的颜色。那么
        case1. index i不跟index (i - 1)同一个颜色 => f[i - 1] * (k - 1)
        case2. index i跟index (i - 1)同一个颜色，那么index (i-1)不能和index (i-2)同一个颜色 =>
               f[i - 2] * (k - 1)
        */