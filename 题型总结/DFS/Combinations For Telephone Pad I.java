/* Given a telephone keypad, and an int number, print all words which are possible by pressing these numbers, the output strings should be sorted.

{0 : "", 1 : "", 2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 : "pqrs", 8 : "tuv", 9 : "wxyz"} 

Assumptions:

The given number >= 0
Examples:

if input number is 231, possible words which can be formed are:

[ad, ae, af, bd, be, bf, cd, ce, df] */

public class Solution {
  public String[] combinations(int number) {
    if (number < 0) {
        return new String[0];
    }
    String[] phone = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    Set<String> set = new HashSet<>();
    dfs(String.valueOf(number).toCharArray(), 0, phone, new StringBuilder(), set);
    String[] results = new String[set.size()];
    int index = 0;
    for (String s : set) {
        results[index++] = s;
    }
    Arrays.sort(results);
    return results;
  }

  private void dfs(char[] sc, int index, String[] phone, StringBuilder sb, Set<String> set) {
    if (index == sc.length) {
        if (sb.length() > 0) {
            set.add(new String(sb).trim());
        }
        return;
    }
    int digit = Integer.parseInt(sc[index] + "");
    for (char c : phone[digit].toCharArray()) {
        sb.append(c);
        dfs(sc, index + 1, phone, sb, set);
        sb.deleteCharAt(sb.length() - 1);
    }
  }
}
//time: O(4^n), space: O(n)