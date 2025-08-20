/* Given a list of word prefix and a sentence, replace the words in the sentence with its prefix if the prefix exists in the given list. If a word has multiple given prefix, replace with the shortest one. Return the sentence after replacement.

Example: 

Input:

prefix = [pro, program, de, re]

sentence = "if debugging is the process of removing bugs programming must be the process of putting them in"

Output: "if de is the pro of re bugs pro must be the pro of putting them in"

Assumptions:

The given sentence only contains space and lower case characters.
The given list of prefix only contains lower case characters.
There is no duplicates in the list of prefix. */

public class Solution {
  public String replaceWords(List<String> prefix, String s) {
    if (prefix == null || prefix.size() == 0) {
      return s;
    }
    Collections.sort(prefix);
    String[] strs = s.split(" ");
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < strs.length; i++) {
      boolean found = false;
      for (String p : prefix) {
        if (strs[i].startsWith(p)) {
          sb.append(p);
          found = true;
          break;
        }
      }
      if (!found) {
        sb.append(strs[i]);
      }
      if (i != strs.length - 1) {
        sb.append(" ");
      }
    }
    return sb.toString();
  }
}
//time: O(nlogn), space: O(n)