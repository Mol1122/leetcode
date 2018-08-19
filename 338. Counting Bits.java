class Solution {
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
** 时间复杂度：O(n) 
** 空间复杂度：O(n) */