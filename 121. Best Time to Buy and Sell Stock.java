class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int p : prices) {
            min = Math.min(min, p);
            profit = Math.max(profit, p - min);
        }
        return profit;
    }
}

/* 算法：数组的遍历
** 时间复杂度：O(n) */