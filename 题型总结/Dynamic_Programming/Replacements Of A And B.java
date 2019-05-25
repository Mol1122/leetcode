/* Given a string with only character 'a' and 'b', replace some of the characters such that the 
string becomes in the forms of all the 'b's are on the right side of all the 'a's.

Determine what is the minimum number of replacements needed.

Assumptions:

The given string is not null.
Examples:

"abaab", the minimum number of replacements needed is 1 (replace the first 'b' with 'a' so that 
the string becomes "aaaab"). */

public class Solution {
  public int minReplacements(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    int n = s.length();
    int aCount = 0; //对于 0-i这段subarray，当前元素i如果调整好之后是a的话，最小的replacement
    int bCount = 0; //对于 0-i这段subarray，当前元素i如果调整好之后是b的话，最小的replacement
    char[] sc = s.toCharArray();

    if (sc[0] == 'a') {
        bCount++;
    } else {
        aCount++;
    }
    for (int i = 1; i < n; i++) {
        if (sc[i] == 'a') {
            bCount = Math.min(bCount, aCount) + 1;
        } else {
            bCount = Math.min(bCount, aCount);
            aCount++;
        }
    }
    return Math.min(aCount, bCount);
  }
}
//time: O(n), space: O(n) -> O(1)

public class Solution {
  public int minReplacements(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    int n = s.length();
    int[][] f = new int[n][2];
    char[] sc = s.toCharArray();
    f[0][0] = 0;
    f[0][1] = 0;
    if (sc[0] == 'a') {
        f[0][1] = 1;
    } else {
        f[0][0] = 1;
    }
    for (int i = 1; i < n; i++) {
       if (sc[i] == 'a') {
           f[i][0] = f[i - 1][0];
           f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + 1;
       } else {
           f[i][0] = f[i - 1][0] + 1;
           f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]);
       }
    }
    return  Math.min(f[n - 1][0], f[n - 1][1]);
  }
}
//f[i][0] = 前i个字符变为a, f[i][1] = 前i个字符变为b 需要的min replacement
//time: O(n), space: O(n)