/* Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2
 */

 class Solution {
    public int scoreOfParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int curr = 0; //the score of current level

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.offerLast(curr); //record the old score
                curr = 0;
            } else {
                curr = stack.pollLast() + Math.max(curr * 2, 1); //previous scrore + curr score (they are in the same level)
            }
        }
        return curr;
    }
}
//time: O(n), space: O(n)

class Solution {
    public int scoreOfParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sc = s.toCharArray();
        int level = 0, result = 0;

        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == '(') {
                level++;
            } else {
                level--;
            }
            if (sc[i] == ')' && sc[i - 1] == '(') { // If we meet "()", we know the number of layer outside
                result += 1 << level;
            }
        }
        return result;
    }
}
//time: O(n), space: O(1)