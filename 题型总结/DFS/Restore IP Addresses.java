/* Given a string containing only digits, restore it by retiring all possible valid IP address combinations.

Input:  ”25525511135”

Output: [“255.255.11.135”, “255.255.111.35”] */

public class Solution {
  public List<String> Restore(String s) {
    List<String> results = new ArrayList<>();
    if (s == null || s.length() == 0) {
        return results;
    }
    dfs(s, 0, 4, "", results);
    return results;
  }

  private void dfs(String s, int index, int part, String ip, List<String> results) {
    if (index == s.length() && part == 0) {
        results.add(ip.substring(0, ip.length() - 1));
        return;
    }
    for (int i = index + 1; i <= index + 3 && i <= s.length(); i++) {
        String sub = s.substring(index, i);
        int num = Integer.parseInt(sub);
        if (sub.length() > 1 && sub.charAt(0) == '0') {
            continue;
        }
        if (num < 0 || num >= 256) {
            continue;
        }
        String temp = ip + sub + ".";
        dfs(s, i, part - 1, temp, results);
    }
  }
}
//time: O(3^n), space: O(n)  
