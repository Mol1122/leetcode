/* Given a word and a dictionary, determine if it can be composed by concatenating words from
the given dictionary.

Assumptions

The given word is not null and is not empty
The given dictionary is not null and is not empty and all the words in the dictionary are not 
null or empty
Examples

Dictionary: {“bob”, “cat”, “rob”}

Word: “robob” return false

Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob" */

public class Solution {
  public boolean canBreak(String input, String[] wordDict) {
      Set<String> dict = new HashSet<>();
      for (String s : wordDict) {
          dict.add(s);
      }
      boolean[] f = new boolean[input.length() + 1];
      f[0] = true;
      for (int i = 1; i <= input.length(); i++) {
          for (int j = 0; j <= i; j++) {
              if (f[j] && dict.contains(input.substring(j, i))) {
                  f[i] = true;
                  break;
              }
          }
      }
      return f[input.length()];
  }
}
//前i个字符是否在字典里出现过
