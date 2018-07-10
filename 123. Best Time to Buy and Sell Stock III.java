class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        
        int min = prices[0];
        left[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }
        
        int max = prices[prices.length - 1];
        right[prices.length - 1] = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }
        
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }
        return profit;
    }
}

/* 算法：动态规划. 从数组的两边分别遍历，找最大的差值. 最后遍历相遇时的最大值两边是互不干预的
** 时间复杂度：O(n) 
** 空间复杂度：O(n) */