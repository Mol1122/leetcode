/* How to cut/split the number into a minimum number of items such that each item is equal to an 
integer's square value.

For example 

4 can be split to 1+1+1+1 (4 items) or 2^2 (1 item, which is the solution)
Return 1
10 can be split to two items 3^2 + 1^2 (solution) or four items 2^2 + 2^2 + 1^2 +1^2
Return 2 */

public Solution {
  public int minCut(int num) {
    if (num <= 1) {
      return num;
    }
    int[] f = new int[num + 1];

    for (int i = 1; i <= num; i++) {
      f[i] = i; //i个1相加
      for (int j = 1; j * j <= i; j++) {
        f[i] = Math.min(f[i], f[i - j * j] + 1);
      }
    }
    return f[num];
  }
}
//f[i] = min # of perfect squares to sum up to i
//f[i] = min(f[i - j ^ 2] + 1) where j >= 1 ^^ j ^ 2 <= 1
//time: O(n^1.5), space: O(n)