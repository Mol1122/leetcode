public class Solution {
    /**
     * @param n: An integer
     * @return: The sum of a and b
     */
    public int dropEggs(int n) {
        long ans = 0;
        for (int i = 0; ; i++) {
            ans += (long)i;
            if (ans >= n) {
                return i;
            }
        }
    }
}

/* 算法：因为若之前在第x层丢的没有碎则应继续往高层找，而此时已经消耗了一次丢鸡蛋的机会，所以只能往上再增加x - 1层。
依次可知, x + (x - 1) + (x - 2) + ... + 2 + 1 >= 100, 求出x最小值是14 */