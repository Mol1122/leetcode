public class Solution {
  public String reverseWords(String s) {
      if (s == null || s.length() == 0) {
          return s;
      }
      char[] sc = s.toCharArray();
      reverse(sc, 0, sc.length - 1);
    
      int index = 0;
      for (int i = 0; i < sc.length; i++) {
          if (sc[i] == ' ') {
              reverse(sc, index, i - 1);
              index = i + 1;
          }
      }
      reverse(sc, index, sc.length - 1);
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

/* 时间复杂度：O(n^2)
** 空间复杂度：O(n) */