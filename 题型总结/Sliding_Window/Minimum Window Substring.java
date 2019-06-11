/* Given a string S and a string T, find the minimum window in S which will contain all the characters in T

Input: S = “ADOBECODEBANC”

          T = “ABC”

Output: “BANC” */

public class Solution {
  public String minWindow(String s, String t) {
    if (s == null || t == null) {
        return "";
    }
    char[] sc = s.toCharArray();
    char[] tc = t.toCharArray();
    int[] cntS = new int [256];
    int[] cntT = new int [256];
    String minStr = "";
    int min = Integer.MAX_VALUE;
    initTargetHash(tc, cntT);
    
    int j = 0;
    for (int i = 0; i < sc.length; i++) {
        while (j < sc.length && !isValid(cntS, cntT)) {
            cntS[sc[j]]++;
            j++;
        }
        if (isValid(cntS, cntT)) { //防止越界了还是invalid
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
//time: O(n*k), space: O(256)