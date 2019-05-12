public class Solution {
  public String rightShift(String s, int n) {
      if (s == null || s.length() == 0 || n <= 0) {
          return s;
      }
      n = n % s.length();
      char[] sc = s.toCharArray();
      reverse(sc, 0, sc.length - n - 1);
      reverse(sc, sc.length - n, sc.length - 1);
      reverse(sc, 0, sc.length - 1);
      
      return new String(sc);
  }
  
  private void reverse(char[] sc, int start, int end) {
      while (start < end) {
          char c = sc[start];
          sc[start] = sc[end];
          sc[end] = c;
          start++;
          end--;
      }
  }
}
/* time: O(n)
** space: O(n) */
