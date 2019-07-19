/* Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1 */

public class Solution {
  public int distance(String[] array, String source, String target) {
    int s = -1, t = -1;
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < array.length; i++) {
      if (array[i].equals(source)) {
        s = i;
      } else if (array[i].equals(target)) {
        t = i;
      }
      if (s != -1 && t != -1) {
        min = Math.min(min, Math.abs(t - s));
      }
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }
}
//time: O(n), space: O(1)