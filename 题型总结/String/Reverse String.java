public class Solution {
  public String reverse(String s) {
      if (s == null || s.length() == 0) {
          return s;
      }
      char[] sc = s.toCharArray();
      int i = 0, j = s.length() - 1;
    
      while (i < j) {
          char c = sc[i];
          sc[i] = sc[j];
          sc[j] = c;
          i++;
          j--;
      }
      return new String(sc);
  }
}
