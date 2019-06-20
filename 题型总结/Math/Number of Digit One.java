/* Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13. */

public class Solution {
  public int countDigitOne(int n) {
    int ones = 0;
    for (long m = 1; m <= n; m *= 10) {
      ones += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? n % m + 1 : 0);
    }
    return ones;
  }
}
//time: O(logn), space: O(1)
/* 这道题让我们比给定数小的所有数中1出现的个数

1的个数	含1数字	数字范围
1	1	[0,9
11	10 11 … 19	[10,19]
1	21	[20.29]
1	31	[30.39]
1	41	[40.49]
1	51	[50,59]
1	61	[60,69]
1	71	[70.79]
1	81	[80,89]
1	91	[90,99]
11	100 101 … 109	[100,109]
21	110 111 … 119	[110,119]
11	120 121 … 129	[120,129]
…	…	…
我们可以发现，如果按照10的整次幂进行分块的话，是具有区块性的，我们可以按照区块去计算即可 
https://leetcode.com/problems/number-of-digit-one/discuss/263878/topic */