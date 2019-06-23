/* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string. */

public class Solution {
  public int strobogrammaticInRange(String low, String high) {
    Map<Character, Character> map = new HashMap<>();
    init(map);
    int cnt = 0;

    for (int i = low.length(); i <= high.length(); i++) {
      cnt += dfs(low, high, new char[i], 0, i - 1, map);
    }
    return cnt;
  }

  private int dfs(String low, String high, char[] temp, int left, int right, Map<Character, Character> map) {
    if (left > right) {
      String s = new String(temp);
      if (temp.length == low.length() && s.compareTo(low) < 0 ||
          temp.length == high.length() && s.compareTo(high) > 0) {
            return 0;
      }
      return 1;
    }
    int cnt = 0;
    for (char c : map.keySet()) {
      temp[left] = c;
      temp[right] = map.get(c);
      if (temp.length != 1 && temp[0] == '0') {
        continue;
      }
      if (left == right && c != map.get(c)) {
        continue;
      } 
      cnt += dfs(low, high, temp, left + 1, right - 1, map);
    }
    return cnt;
  }

  private void init(Map<Character, Character> map) {
    map.put('0', '0');
    map.put('1', '1');
    map.put('6', '9');
    map.put('8', '8');
    map.put('9', '6');
  }
}
//time: O(5^n), space: O(n)