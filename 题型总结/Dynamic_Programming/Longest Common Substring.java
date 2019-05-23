public class Solution {
  public String longestCommon(String A, String B) {
    if (A == null || B == null || A.length() == 0 || B.length() == 0) {
        return "";
    }
    int[][] f = new int[A.length() + 1][B.length() + 1];

    for (int i = 1; i <= A.length(); i++) {
        for (int j = 1; j <= B.length(); j++) {
            if (A.charAt(i - 1) == B.charAt(j - 1)) {
                f[i][j] = f[i - 1][j - 1] + 1;
            } 
        }
    }
    int max = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= A.length(); i++) {
        for (int j = 0; j <= B.length(); j++) {
            if (f[i][j] > max) {
                max = f[i][j];
                sb = new StringBuilder(A.substring(i - f[i][j], i));
            }
        }
    }
    return sb.toString();
  }
}
//f[i][j] = largest common substring ending at index i - 1 of A and index j - 1 of B
//time: O(n^2), space: O(n^2)
