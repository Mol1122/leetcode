class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int pre_sell = 0, sell = 0, pre_buy = Integer.MIN_VALUE, buy = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            pre_buy = buy;
            buy = Math.max(pre_sell - prices[i], pre_buy);
            pre_sell = sell;
            sell = Math.max(pre_buy + prices[i], pre_sell);
        }
        return sell;
    }
}

/* 算法：动态规划，序列型dp
        state:  by defination, we can get
                    buy[i]  = max(rest[i-1]-price, buy[i-1]) 
                    sell[i] = max(buy[i-1]+price, sell[i-1])
                    rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
                since rest[i - 1] > buy[i] and sell[i] > rest[i]    
        buy[i] = max(sell[i-2]-price, buy[i-1])
        sell[i] = max(buy[i-1]+price, sell[i-1])
*/