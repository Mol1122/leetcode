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

/* 算法：其实就是Pow(x, n)的高配版，就是在每一步都mod b
** 难点：每一步都要mod b 
** 时间复杂度：O(logn) */