/* Given an integer array, what is the minimum number of operations to convert it to an ascending array.

One operation you can move one element of the array to another position.

Assumption:

There would not be duplicate elements in the array.

Examples:

{1, 3, 2, 4}, the least moves needed is 1, move 2 to the middle of 1 and 3. */

public class Solution {
  public int leastMoves(int[] array) {
    int mostNotMoves = longestAscendingSubSequence(array);
    return array.length - mostNotMoves;
  }

  private int longestAscendingSubSequence(int[] array) {
      int longest = 1;
      int[] dp = new int[array.length];
      dp[0] = 1;
      for(int i = 1 ; i < array.length ; i++) {
          dp[i] = 1;
          for(int j = 0 ; j < i ; j++) {
              if(array[i] > array[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
              }
          }
          longest = Math.max(dp[i], longest);
      }
      return longest;
  }
}
//算法：这道题等价于longest ascending subsequence
//time: O(n^2), space: O(n)