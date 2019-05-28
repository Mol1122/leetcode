/* Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.

Assumptions

l, m, n >= 0
l + m + n > 0
Examples

l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]

Additional Priority Restriction {} > [] > ()
 */
public class Solution {
	public List<String> validParenthesisII(int l, int m, int n) {
		List<String> results = new ArrayList<>();
		final char[] paren = {'(', ')', '[', ']', '{', '}'};
		int[] remain = {l, l, m, m, n, n};
		Deque<Character> stack = new ArrayDeque<>();
		int targetLen = 2 * l + 2 * m + 2 * n;
		dfs(paren, remain, targetLen, stack, new StringBuilder(), results);
		return results;
	}

	private void dfs(char[] paren, int[] remain, int targetLen, Deque<Character> stack, StringBuilder sb, List<String> results) {
		if (sb.length() == targetLen) {
			results.add(new String(sb));
			return;
		}
		for (int i = 0; i < remain.length; i++) {
			if (i % 2 == 0) {
				if (remain[i] > 0 && isValid(paren[i], stack)) {
					sb.append(paren[i]);
					stack.offerLast(paren[i]);
					remain[i]--;
					dfs(paren, remain, targetLen, stack, sb, results);
					remain[i]++;
					stack.pollLast();
					sb.deleteCharAt(sb.length() - 1);
				}
			} else {
				if (!stack.isEmpty() && stack.peekLast() == paren[i - 1]) {
					sb.append(paren[i]);
					stack.pollLast();
					remain[i]--;
					dfs(paren, remain, targetLen, stack, sb, results);
					remain[i]++;
					stak.offerLast(paren[i - 1]);
					sb.deleteCharAt(sb.length() - 1);
				}
			}
		}
	}

	private boolean isValid(char curr, Deque<Character> stack) {
		if (curr == '{') {
			if (!stack.isEmpty() && (stack.peekLast() == '(' || stack.peekLast() == '[')) {
				return false;
			}
		} else if (curr == '[') {
			if (!stack.isEmpty() && stack.peekLast() == '(') {
				return false;
			}
		}
		return true;
	}
}