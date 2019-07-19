/* A message containing letters from A-Z is being encoded to numbers using the following ways:

         ‘A’ = 1

         ‘B’ = 2

         …

         ‘Z’ = 26

Given an encoded message containing digits, determine the total number of ways to decode it.

Input:    “212”

It can be either decoded as 2,1,2("BAB") or 2,12("BL") or 21,2("UB"), return 3. */

public class Solution {
  public int numDecodeWay(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] sc = s.toCharArray();
    int n = s.length();
    int[] f = new int[n + 1];
    f[0] = 1;

    for (int i = 1; i <= sc.length; i++) {
      f[i] = 0;
      if (sc[i - 1] != '0') {
        f[i] += f[i - 1];
      }
      if (i >= 2) {
        int value = (sc[i - 2] - '0') * 10 + (sc[i - 1] - '0');
        if (value >= 10 && value <= 26) {
          f[i] += f[i - 2];
        }
      }
    }
    return f[n];
  }
}
//time: O(n), space: O(n)