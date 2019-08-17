/* Given a string, find the longest substring without any repeating characters and return the length of it. 
The input string is guaranteed to be not null.

For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", we should return 4 in this case. */

public class Solution {
  public int longest(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    Set<Character> set = new HashSet<>();
    int max = 0;
    int j = 0;
    for (int i = 0; i < s.length(); i++) {
        while (j < s.length() && !set.contains(s.charAt(j))) {
            set.add(s.charAt(j));
            j++;
        }
        max = Math.max(max, j - i);
        if (set.contains(s.charAt(i))) {
            set.remove(s.charAt(i));
        }
    }
    return max;
  }
}    
//time: O(n), space: O(n)