/* Given two integers num and k, consider a set of positive integers with the following properties:

The units digit of each integer is k.
The sum of the integers is num.
Return the minimum possible size of such a set, or -1 if no such set exists.

Note:

The set can contain multiple instances of the same integer, and the sum of an empty set is considered 0.
The units digit of a number is the rightmost digit of the number.
 

Example 1:

Input: num = 58, k = 9
Output: 2
Explanation:
One valid set is [9,49], as the sum is 58 and each integer has a units digit of 9.
Another valid set is [19,39].
It can be shown that 2 is the minimum possible size of a valid set.
Example 2:

Input: num = 37, k = 2
Output: -1
Explanation: It is not possible to obtain a sum of 37 using only integers that have a units digit of 2.
Example 3:

Input: num = 0, k = 7
Output: 0
Explanation: The sum of an empty set is considered 0.
 */

class Solution {
    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        if (k == 0) {
            if (num % 10 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        
        return dfs(num, k, memo);
    }
    
    private int dfs(int num, int k, Map<Integer, Integer> memo) {
        if (memo.containsKey(num)) {
            return memo.get(num);
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i * 10 + k <= num; i++) {
            int count = dfs(num - (i * 10 + k), k, memo);
            if (count != -1) {
                result = Math.min(result, count + 1);
            }
        }
        memo.put(num, result == Integer.MAX_VALUE ? -1 : result);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}