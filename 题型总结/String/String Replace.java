/* Given an original string input, and two strings S and T, replace all occurrences of S in input with T.

Assumptions

input, S and T are not null, S is not empty string
Examples

input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
input = "dodododo", S = "dod", T = "a", input becomes "aoao" */

public class Solution {
  public String replace(String input, String s, String t) {
      if (input == null || input.length() == 0) {
          return input;
      }
      char[] sc = input.toCharArray();
      if (s.length() >= t.length()) { //t短就不会overwrite
          return replaceShort(sc, s, t);
      } else {
          return replaceLong(sc, s, t);
      }
  }
  
  private String replaceLong(char[] sc, String s, String t) {
      List<Integer> matches = getAllMatches(sc, s);
      char[] result = new char[sc.length + matches.size() * (t.length() - s.length())];
      int lastIndex = matches.size() - 1;
      int slow = result.length - 1; //从后往前遍历
      int fast = sc.length - 1;
    
      while (fast >= 0) {
          if (lastIndex >= 0 && fast == matches.get(lastIndex)) {
              copySubstring(result, slow - t.length() + 1, t);
              slow -= t.length();
              fast -= s.length();
              lastIndex--;
          } else {
              result[slow--] = sc[fast--];
          }
      }
      return new String(result);
  }
  
  private List<Integer> getAllMatches(char[] sc, String s) {
      List<Integer> results = new ArrayList<>();
      int i = 0;
    
      while (i <= sc.length - s.length()) {
          if (equalsSubstring(sc, i, s)) {
              results.add(i + s.length() - 1); //end index
              i += s.length();
          } else {
              i++;
          }
      }
      return results;
  }
  
   private String replaceShort(char[] sc, String s, String t) {
       int i = 0, j = 0;
       while (j < sc.length) {
           if (j <= sc.length - s.length() && equalsSubstring(sc, j, s)) {
               copySubstring(sc, i, t);
               i += t.length();
               j += s.length();
           } else {
               sc[i++] = sc[j++];
           }
       }
       return new String(sc, 0, i);
   }
  
   private void copySubstring(char[] sc, int start, String t) {
       for (int i = 0; i < t.length(); i++) {
           sc[start + i] = t.charAt(i);
       }
   }
  
   private boolean equalsSubstring(char[] sc, int start, String s) {
       for (int i = 0; i < s.length(); i++) {
           if (sc[start + i] != s.charAt(i)) {
               return false;
           }
       }
       return true;
   }
}

//time：O(k*n), space: O(n) 