/* Repeatedly remove all adjacent, repeated characters in a given string from left to right.

No adjacent characters should be identified in the final string.

Examples

"abbbaaccz" → "aaaccz" → "ccz" → "z"
"aabccdc" → "bccdc" → "bdc" */

public class Solution {
  public String deDup(String s) {
      if (s == null || s.length() == 0) {
          return s;
      }
      char[] sc = s.toCharArray();
      int i = 0, j = 1;
    
      while (j < sc.length) {
          if (i == -1 || sc[j] != sc[i]) { //不重复的话，让i指向j所在的元素
              sc[++i] = sc[j++];
          } else {
              while (j < sc.length && sc[j] == sc[i]) {
                  j++;
              }
              i--; //因为重复的元素一个也不能保留
          }
      }
      return new String(sc, 0, i + 1);
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */
