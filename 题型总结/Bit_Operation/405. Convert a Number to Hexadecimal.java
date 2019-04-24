class Solution {
    public String toHex(int num) {
      if (num == 0) {
          return "0";
      }
      char[] base = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                     'a', 'b', 'c', 'd', 'e', 'f'};
      StringBuilder sb = new StringBuilder();
      boolean isLeading = true;
      for (int i = 28; i >= 0; i -= 4) {
          char c = base[(num >>> i) & 15];
          if (c != '0' || !isLeading) {
              sb.append(c);
              isLeading = false;
          }
      }
      return sb.toString();
       
    }
}
//4个二进制位=1个十六进制位