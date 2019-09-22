/* There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. 
On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). 
For the i-th round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find 
how many bulbs are on after n rounds.

Example:

Input: 3
Output: 1 
Explanation: 
At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on. */

class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}

/* 我们发现对于所有的非完全平方数，其因子个数均为偶数，则最后结果为灯灭。
所以最后只有编号恰好为完全平方数的灯亮着，因此答案为\sqrt{n} */
