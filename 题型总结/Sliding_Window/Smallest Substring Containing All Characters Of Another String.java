/* Given two strings s1 and s2, find the shortest substring in s1 containing all characters in s2.

If there does not exist such substring in s1, return an empty string.

Assumptions:

s1 and s2 are not null or empty.
Examples:

s1= “The given test strings”

s2: “itsst”

Output string: “st stri” */

public class Solution {
  public String smallest(String s, String t) {
    if (s == null || t == null) {
        return "";
    }
    char[] sc = s.toCharArray();
    char[] tc = t.toCharArray();
    int[] cntS = new int[256];
    int[] cntT = new int[256];
    String minStr = "";
    int min = Integer.MAX_VALUE;
    initTargetHash(tc, cntT);

    int j = 0;
    for (int i = 0; i < sc.length; i++) {
        while (j < sc.length && !isValid(cntS, cntT)) {
            cntS[sc[j]]++;
            j++;
        }
        if (isValid(cntS, cntT)) {
            if (min > j - i) {
                min = j - i;
                minStr = s.substring(i, j);
            }
        }
        cntS[sc[i]]--;
    }
    return minStr;
  }

  private boolean isValid(int[] cntS, int[] cntT) {
    for (int i = 0; i < 256; i++) {
        if (cntT[i] > cntS[i]) {
            return false;
        }
    }
    return true;
  }

  private void initTargetHash(char[] tc, int[] cntT) {
    for (char c : tc) {
        cntT[c]++;
    }
  }
}
//time: O(nk), space: O(256)
