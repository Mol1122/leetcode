/* Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

            great

             /    \

           gr    eat

          / \      /  \

         g   r  e   at

                      / \

                    a   t

To scramble the string, we may choose any non-leaf node and swap its two children. For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".    

                  rgeat

                   /    \

                rg    eat

               / \      /  \

             r   g   e   at

                     / \

                   a   t

  We say that "rgeat" is a scrambled string of "great". Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

              rgtae

              /    \

            rg    tae

           / \    /  \

          r   g  ta  e

                  / \

                 t   a

We say that "rgtae" is a scrambled string of "great". Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1. */

public class Solution {
  Map<String, Boolean> map = new HashMap<>();

  public boolean isScramble(String s1, String s2) {
    if (s1 == null || s2 == null || s1.length() != s2.length()) {
      return false;
    }
    if (s1.length() == 0 && s2.length() == 0) {
      return true;
    }
    if (map.containsKey(s1 + "#" + s2)) {
      return map.get(s1 + "#" + s2);
    }
    int n = s1.length();
    if (n == 1) {
      return s1.charAt(0) == s2.charAt(0);
    }
    for (int i = 1; i < n; i++) {
      if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
          isScramble(s1.substring(i, n), s2.substring(i, n)) ||
          isScramble(s1.substring(0, i), s2.substring(n - i, n)) &&
          isScramble(s1.substring(i, n), s2.substring(0, n - i))) {
            map.put(s1 + "#" + s2, true);
            return true;
      }
    }
    map.put(s1 + "#" + s2, false);
    return false;
  }
}
//time: O(n^n), space: O(n)
