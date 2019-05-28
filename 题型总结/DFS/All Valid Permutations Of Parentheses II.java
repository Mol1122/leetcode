/* Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.

Assumptions

l, m, n >= 0
l + m + n > 0
Examples

l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]

 */
 
public class Solution {
  public List<String> validParentheses(int l, int m, int n) {
    final char[] paren = new char[] {'(', ')', '<', '>', '{', '}'};
    int[] remain = {l, l, m, m, n, n};
    int targetLen = 2 * l + 2 * m + 2 * n;
    List<String> results = new ArrayList<>();
    Deque<Character> stack = new LinkedList<>();
    dfs(paren, remain, targetLen, stack, new StringBuilder(), results);
    return results;
  }

  private void dfs(char[] paren, int[] remain, int targetLen, Deque<Character> stack, 
                    StringBuilder sb, List<String> results) {
    if (sb.length() == targetLen) {
        results.add(sb.toString());
        return;
    }
    for (int i = 0; i < remain.length; i++) {
        if (i % 2 == 0) { //left paren
            if (remain[i] > 0) { //只要左括号还有剩余，都可以加
                sb.append(paren[i]);
                stack.offerFirst(paren[i]);
                remain[i]--;
                dfs(paren, remain, targetLen, stack, sb, results);
                sb.deleteCharAt(sb.length() - 1);
                stack.pollFirst();
                remain[i]++; 
            }
        } else { //right paren
            if (!stack.isEmpty() && stack.peekFirst() == paren[i - 1]) {
                sb.append(paren[i]);
                stack.pollFirst();
                remain[i]--;
                dfs(paren, remain, targetLen, stack, sb, results);
                sb.deleteCharAt(sb.length() - 1);
                stack.offerFirst(paren[i - 1]);
                remain[i]++;
            }
        }
    }
  }
}
//time: O(2^(2n)), space: O(2n)