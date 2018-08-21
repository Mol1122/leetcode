public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackVI(int[] A, int m) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] f = new int[m + 1];
        f[0] = 1;
        
        //跟coin change的解法一样
        for (int j = 0; j <= m; j++) {
            //遍历最后放入背包的物品
            for (int i = 0; i < n; i++) {
                if (j - A[i] >= 0) {
                    f[j] += f[j - A[i]];
                }
            }
        }
        return f[m];
    }
}

/* 算法：f[i] = 有多少种组合能拼出重量i
    f[i] = f[i-A0] + f[i-A1] +…+ f[i-A[N-1]]
** 难点：跟之前的题不同的地方在于，之前是判断数组的最后一个item要不要放进背包。
         这题判断的是放进背包的是哪一个item */