class Solution {
    public int change(int amount, int[] coins) {
//         int n = coins.length;
//         int[][] f = new int[n + 1][amount + 1]; //前i种物品拼成重量为j的方式有多少种
//         for (int i = 0; i <= n; i++) {
//             f[i][0] = 1;
//         }
        
//         for (int i = 1; i <= coins.length; i++) {
//             for (int j = 1; j <= amount; j++) {
//                 f[i][j] += f[i - 1][j];
//                 if (j >= coins[i - 1]) {
//                     f[i][j] += f[i][j - coins[i - 1]];
//                 }
//             }
//         }
//         return f[n][amount];
        
        //终极优化
        int n = coins.length;
        int[] f = new int[amount + 1]; //前i种物品拼成重量为j的方式有多少种
        f[0] = 1;
        
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    f[j] += f[j - coins[i - 1]];
                }
            }
        }
        return f[amount];
    }
}

/* 完全背包问题。这题跟combination sum的区别在于，combination sum里顺序不一样算作不同的方式，但是这里顺序不一样算同一种方式。combination sum的最后一步考虑数字放几，假设放1，之前的f包含了1、2, 如果放了2,那么之前的包含了1、1所以是不能去重的。这里用完全背包问题去思考，这样的最后一步是最后一个数字放不放进去，数字遍历是有顺序的，所以可以去重 */
