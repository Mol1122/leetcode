/* Given a string S and a string T, find the minimum window in S which will contain all the characters in T

Input: S = “ADOBECODEBANC”

          T = “ABC”

Output: “BANC” */

public class Solution {
  public String minWindow(String s, String t) {
    if (s == null || t == null) {
        return "";
    }
    int min = Integer.MAX_VALUE;
    String minStr = "";
    int[] cntS = new int[256];
    int[] cntT = new int[256];
    initTargetHash(cntT, t);

    int j = 0;
    for (int i = 0; i < s.length(); i++) {
        while (j < s.length() && !isValid(cntS, cntT)) {
            cntS[s.charAt(j)]++;
            j++;
        }
        if (isValid(cntS, cntT)) { //以防一直走到最后都找不到valid
            if (j - i < min) {
                min = j - i;
                minStr = s.substring(i, j);
            }
        }
        cntS[s.charAt(i)]--;
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

  private void initTargetHash(int[] cntT, String t) {
    for (char c : t.toCharArray()) {
        cntT[c]++;
    }
  }
}
//time: O(n), space: O(256)