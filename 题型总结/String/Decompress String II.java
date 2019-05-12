public class Solution {
  public String decompress(String s) {
      if (s == null || s.length() == 0) {
          return s;
      }
      char[] sc = s.toCharArray();
      StringBuilder sb = new StringBuilder();
    
      for (int i = 0; i < sc.length; i++) {
          char c = sc[i++];
          int count = sc[i] - '0';
          for (int j = 0; j < count; j++) {
              sb.append(c);
          }
      }
      return new String(sb);
  }
}

//time: O(n)
//space: O(n) due to StringBuilder
