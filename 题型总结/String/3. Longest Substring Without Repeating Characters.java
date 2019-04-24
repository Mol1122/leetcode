public class Solution {
  public int longest(String s) {
      if (s == null || s.length() == 0) {
          return 0;
      }
      int slow = 0, fast = 0;
      int max = 0;
      Set<Character> set = new HashSet<>();
    
      while (fast < s.length()) {
          if (!set.contains(s.charAt(fast))) {
              set.add(s.charAt(fast));
              fast++;
              max = Math.max(max, fast - slow);
          } else {
              set.remove(s.charAt(slow));
              slow++;
          }
      }
      return max;
  }
}
//time: O(n), space: O(n)