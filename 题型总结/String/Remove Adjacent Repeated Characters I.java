public class Solution {
  public String deDup(String s) {
      if (s == null || s.length() == 0) {
          return s;
      }
      char[] sc = s.toCharArray();
      int i = 1;
      for (int j = 1; j < sc.length; j++) {
          if (sc[j] != sc[i - 1]) {
              sc[i] = sc[j];
              i++;
          }
      }
      return new String(sc, 0, i);
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */
