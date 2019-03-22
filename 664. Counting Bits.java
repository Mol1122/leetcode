public class Solution {
    /**
     * @param num: a non negative integer number
     * @return: an array represent the number of 1's in their binary
     */
    public int[] countBits(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            f[i] = f[i >> 1] + (i % 2);
        }
        return f;
    }
}
/* 算法：位操作型动态规划一般用值作状态
** 时间复杂度：O(nlogn), 因为大约有n/2个数有logn位进制位
** 空间复杂度：O(n) */