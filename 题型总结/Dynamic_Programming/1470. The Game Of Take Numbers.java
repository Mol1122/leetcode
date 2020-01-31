/* 现在有一个数组arr。有两个玩家1号和2号轮流从数组中取数，他们只能从数组的两头进行取数，
且一次只能取一个。两人都采取最优策略。到最后数组中的数被取完后，谁取的数的总和多，就赢得胜利。
1号玩家先取。问最后谁将获胜。若1号玩家必胜或两人打成平局，返回1，若2号玩家必胜，返回2。 */

public class Solution {
    /**
     * @param arr: the array
     * @return: the winner
     */
    public int theGameOfTakeNumbers(int[] A) {
        if (A == null || A.length == 0) {
            return 2;
        }
        int n = A.length;
        int[][] f = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            f[i][i] = A[i]; 
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(-f[i + 1][j] + A[i], f[i][j - 1] + A[j]);
            }
        }
        return f[0][n - 1] >= 0 ? 1 : 2;
    }
}
//f[i][j] = max diff the current player can get for [i, j]
// A >= B, A - B >= 0, we want to maximize the A - B.
/* 考点：

思维博弈
题解：
数组长度为偶数，一号玩家每次游戏后的和可为max(奇数项和，偶数项和)，故一定是一号玩家获胜。 */