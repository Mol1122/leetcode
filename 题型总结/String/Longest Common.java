/* Write a function to find the longest common prefix string amongst an array of strings. */

public class Solution {
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
      int j = 0;
      while (j < prefix.length() && j < strs[i].length() && prefix.charAt(j) == strs[i].charAt(j)) {
        j++;
      }
      if (j == 0) {
        return "";
      }
      prefix = prefix.substring(0, j);
    }
    return prefix;
  }
}
//time: O(nl), space: O(1)
