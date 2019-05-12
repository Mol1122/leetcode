public class Solution {
  public List<Integer> allAnagrams(String s, String l) {
      List<Integer> results = new ArrayList<>();
      if (l == null || s == null || s.length() > l.length()) {
          return results;
      }
      Map<Character, Integer> map = getCount(s);
      int match = 0;
    
      for (int i = 0; i < l.length(); i++) {
          //add right
          char right = l.charAt(i);
          Integer count = map.get(right); //s里有多少个right char
          if (count != null) { //只关心在s里的char
              map.put(right, count - 1);
              if (count == 1) {
                  match++;
              }
          }
          //remove left
          if (i >= s.length()) {
              char left = l.charAt(i - s.length());
              count = map.get(left);
              if (count != null) {
                  map.put(left, count + 1);
                  if (count == 0) {
                      match--;
                  }
              }
          }
          if (match == map.size()) { //因为前面保证了sliding window的长度，所以当map里面的数完全match, sliding window的长度和s是一样的
              results.add(i - s.length() + 1);
          }
      }
      return results;
  }
  
  private Map<Character, Integer> getCount(String s) {
      Map<Character, Integer> map = new HashMap<>();
      for (char c : s.toCharArray()) {
          map.put(c, map.getOrDefault(c, 0) + 1);
      }
      return map;
  }
}

//time: O(n), space: O(n)
