/* 现在有一个卡牌游戏，先给出卡牌的数量n，再给你两个非负整数totalProfit、totalCost，然后给出每张卡牌的利润值 a[i]和成本值b[i]，
现在可以从这些卡牌中任意选择若干张牌，组成一个方案，问有多少个方案满足所有选择的卡牌利润和大于totalProfit且成本和小于totalCost。 */

public class Solution {
    public int numOfPlan(int n, int totalProfit, int totalCost, int[] a, int[] b) {
        long[][][] dp = new long[150][150][150];
        long mod = 1000000007;
        
        dp[0][0][0] = 1;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i <= totalProfit + 1; i++) {
                for (int j = 0; j < totalCost; j++) {
                    if (dp[k][i][j] > 0) {
                        dp[k + 1][i][j] += dp[k][i][j];
                        dp[k + 1][i][j] %= mod;
                        
                        if (j + b[k] < totalCost) {
                            dp[k + 1][Math.min(totalProfit + 1, i + a[k])][j + b[k]] += dp[k][i][j];
                            dp[k + 1][Math.min(totalProfit + 1, i + a[k])][j + b[k]] %= mod;
                        }
                    }
                }
            }
        }
        long sum = 0;
        for (int cost = 0; cost < totalCost; cost++) {
            sum = (sum +  dp[n][totalProfit + 1][cost]) % mod;
        }
        return (int)sum;
    }
}
/* 假设dp[i][j]dp[i][j]为卡牌利润和等于ii且成本和等于jj的方案数。 则按顺序枚举每一个卡牌xx，同时更新dpdp数组，有： 
dp[i+a[x]][j+b[x]] += dp[i][j]dp[i+a[x]][j+b[x]]+=dp[i][j]

dp递推关系式的推导可以类比背包问题，代码中的totalProfit+1即表示利润大于totalProfit的方案数，totalCost同理，因为有：
int now_p = min(totalProfit + 1, p + a[i]);
int now_c = min(totalCost + 1, c + b[i]);
限制Profit最大为totalProfit + 1，Cost最大为totalCost + 1。

时间复杂度：O(n * totalProfit * totalCost+totalCost)O(n∗totalProfit∗totalCost+totalCost) */
