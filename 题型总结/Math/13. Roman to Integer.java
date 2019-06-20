/* Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000 */

public class Solution {
  public int romanToInt(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);

    int n = s.length();
    int result = map.get(s.charAt(n - 1));

    for (int i = n - 2; i >= 0; i--) {
      if (map.get(s.charAt(i + 1)) <= map.get(s.charAt(i))) {
        result += map.get(s.charAt(i));
      } else {
        result -= map.get(s.charAt(i));
      }
    }
    return result;
  }
}
//time: O(n), space: O(n)