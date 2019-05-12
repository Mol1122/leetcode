public class Solution {
  public boolean allUnique(String s) {
    /*  boolean[] visited = new boolean[256];
      for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          if (visited[c]) {
              return false;
          }
          visited[c] = true;
      }
      return true;  */
    
     if (s == null || s.length() == 0) {
          return true;
      }
      int[] bit_vector = new int[8];
      for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          int row = c / 32;
          int col = c % 32;
          int weight = (1 << col);
          if ((bit_vector[row] & weight) != 0) {
              return false;
          }
          bit_vector[row] |= weight;
      }
      return true;
  }
}
//利用bit operation去省空间
//32 bits = 4 byte = integer
//how many bits do we need to represent ACCII? 255
//256 / 32 = 8 integers to represent 256 bits
// 'b' = 98
// 98 / 32 = 3 -> row; 98 % 32 = 2 -> col