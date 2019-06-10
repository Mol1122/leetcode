/* This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list. */

public class Solution {
  public int shortestWordDistance(String[] words, String word1, String word2) {
    if (words == null || words.length == 0) {
        return -1;
    }
    int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;
    for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1) && words[i].equals(word2)) {
            p2 = p1;
            p1 = i; //sliding this pointers, 这样word1 == word2的时候，index不一样
        } else {
            if (words[i].equals(word1)) {
                p1 = i;
            }
            if (words[i].equals(word2)) {
                p2 = i;
            }
        }
        if (p1 != -1 && p2 != -1) {
            min = Math.min(min, Math.abs(p1 - p2));
        }
    }
    return min;
  }
}
//time: O(nk), space: O(1)