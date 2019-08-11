/* Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:

Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb" */

public class Solution {
  public String removeDuplicateLetters(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    Deque<Character> deque = new ArrayDeque<>();
    int[] cnt = new int[26];
    boolean[] visited = new boolean[26];
    for (char c : s.toCharArray()) {
      cnt[c - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      cnt[c - 'a']--;
      if (visited[c - 'a']) {
        continue;
      }
      while (!deque.isEmpty() && c < deque.peekLast() && cnt[deque.peekLast() - 'a'] > 0) {
        visited[deque.pollLast() - 'a'] = false;;
      }
      deque.offerLast(c);
      visited[c - 'a'] = true;
    }
    StringBuilder sb = new StringBuilder();
    while (!deque.isEmpty()) {
      sb.append(deque.pollFirst());
    }
    return sb.toString();
  }
}
//time: O(n), space: O(n)