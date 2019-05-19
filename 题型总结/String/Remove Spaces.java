/* Given a string, remove all leading/trailing/duplicated empty spaces.

Assumptions:

The given string is not null.
Examples:

“  a” --> “a”
“   I     love MTV ” --> “I love MTV” */

public class Solution {
  public String removeSpaces(String input) {
      if (input == null || input.length() == 0) {
          return input;
      }
      char[] sc = input.toCharArray();
      int left = 0, right = 0;
     /* while (right < sc.length) {
          if (sc[right] == ' ') {
              if (right == 0 || sc[right - 1] == ' ') {
                  right++;
              } else {
                  sc[left] = sc[right];
                  left++;
                  right++;
              }
          } else {
              sc[left] = sc[right];
              left++;
              right++;
          }  
        } */
        while (right < sc.length) {
            if (sc[right] == ' ') {
                if (right == 0 || sc[right - 1] == ' ') {
                    right++;
                    continue;
                }
            } 
            sc[left++] = sc[right++];
        }
      
    //postprocessing, we might have tailing space character at the end
      if (left != 0 && sc[left - 1] == ' ') {
          return new String(sc, 0, left - 1);
      }
      return new String(sc, 0, left);
  }
}
/* 
case1: sc[right] == ' '
      case1.1: right == 0 -> ignore
      case1.2: right != 0 && sc[right] == ' ' -> ingore
      case1.3: else -> keep
case2: sc[right] != ' ' -> keep


时间复杂度：O(n)
空间复杂度：O(1)
*/