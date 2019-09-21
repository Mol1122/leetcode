/* Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S. */

class Solution {
     public String minWindow(String s, String t) {
        int ans = Integer.MAX_VALUE;
        String minStr = "";
        
        int[] cntS = new int[256];
        int[] cntT = new int[256];
        initTargetHash(cntT, t);
        int i = 0, j = 0;
        for (i = 0; i < s.length(); i++) {
            while (j < s.length() && !valid(cntS, cntT)) {
                cntS[s.charAt(j)]++;
                j++;
            }
            if (valid(cntS, cntT)) {
                if (ans > j - i) {
                    ans = j - i;
                    minStr = s.substring(i, j);
                }
            }
            cntS[s.charAt(i)]--;
        }
        return minStr;
    }
    private void initTargetHash(int[] cntT, String t) {
        for (char c : t.toCharArray()) {
            cntT[c]++;
        }
    }
    private boolean valid(int[] cntS, int[] cntT) {
        for (int i = 0; i < 256; i++) {
            if (cntT[i] > cntS[i]) {
                return false;
            }
        }
        return true;
    }
}
//time: O(256 * n), space: O(256)
    
    //follow up:如果允许一个字母不一样
//     public String minWindow(String s, String t) {
//         if (t == null || t.length() == 0) {
//             return "";
//         }
//         int ans = Integer.MAX_VALUE;
//         String minStr = "";
        
//         if (t.length() == 1) {
//             return s.charAt(0) + "";
//         }
//         int[] cntS = new int[256];
//         int[] cntT = new int[256];
//         initTargetHash(cntT, t);
//         int i = 0, j = 0;
//         for (i = 0; i < s.length(); i++) {
//             j = i;
//             while (j < s.length() && !valid(cntS, cntT)) {
//                 cntS[s.charAt(j)]++;
//                 j++;
//             }
//             if (valid(cntS, cntT)) {
//                 if (ans > j - i && j - i >= t.length()) { //这里改动
//                     ans = j - i;
//                     minStr = s.substring(i, j);
//                 }
//             }
//             cntS[s.charAt(i)]--;
//         }
//         return minStr;
//     }
//     private void initTargetHash(int[] cntT, String t) {
//         for (char c : t.toCharArray()) {
//             cntT[c]++;
//         }
//     }
//     private boolean valid(int[] cntS, int[] cntT) {
//         int count = 0; //这里改动
//         for (int i = 0; i < 256; i++) {
//             if (cntT[i] > cntS[i]) {
//                 count++;
//             }
//         }
//         return count  <= 1;
//     }
// }