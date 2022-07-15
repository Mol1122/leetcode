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
        if (i == -1 || sc[i] != sc[j]) { //不重复的话，让i指向j所在的元素
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
// [0, i] -> numbers we want to keep
// (i + 1, j) -> we don't care
// [j, n] -> unexplored
// time: O(1), space: O(n)
// 难点：对比 Dedupllicate elements, not retain any(不需要repeat do that, 只需要把相邻的重复元素删掉即可)

public class Solution {
  public String deDup(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    char[] sc = s.toCharArray();
    Deque<Character> stack = new ArrayDeque<>();
    boolean flag = false;

    for (int i = 0; i < sc.length; i++) {
      while (!stack.isEmpty() && sc[i] == stack.peekLast()) {
        flag = true;
        stack.pollLast();
      }
      if (flag) {
        while (i + 1 < sc.length && sc[i + 1] == sc[i]) {
          i++;
        }
        flag = false;
      } else {
        stack.offerLast(sc[i]);
        flag = false;
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pollFirst());
    }
    return sb.toString();
  }
}
