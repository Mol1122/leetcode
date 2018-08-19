public class Solution {
    /**
     * @param nums: the array
     * @return: the minimum times to flip digit
     */
    public int flipDigit(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][] f = new int[n + 1][2];
        
        f[0][0] = f[0][1] = 0;
        
        for (int i = 1; i <= n; i++) {
            //最后一个数变为什么
            for (int j = 0; j < 2; j++) {
                f[i][j] = Integer.MAX_VALUE;
                int t = 0;
                if (j != A[i - 1]) { //原来的数跟我们想变成的不一样
                    t++;
                }
                //倒数第二个数变为什么
                for (int k = 0; k < 2; k++) {
                    if (k == 0 && j == 1) {
                        continue;
                    }
                    
                    f[i][j] = Math.min(f[i][j], f[i - 1][k] + t);
                }
            }
        }
        return Math.min(f[n][0], f[n][1]);
    }
}

/* 算法：状态f[i][j] = 前i个数中最后一个数变成j的最小翻转次数 */