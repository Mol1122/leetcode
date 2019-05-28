/* Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid. The brackets must close in the correct order.

Examples

"()" and "()[]{}", "[{()}()]" are all valid but "(]" and "([)]" are not. */

public class Solution {
  public boolean isValid(String s) {
    if (s == null || s.length() == 0){
        return true;
    }
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '[' || c == '{') {
            stack.offerLast(c);
        } else {
            if (c == ')') {
                if (!stack.isEmpty() && stack.peekLast() == '(') {
                    stack.pollLast();
                } else {
                    return false;
                }
            } else if (c == ']') {
                if (!stack.isEmpty() && stack.peekLast() == '[') {
                    stack.pollLast();
                } else {
                    return false;
                }
            } else if (c == '}') {
                if (!stack.isEmpty() && stack.peekLast() == '{') {
                    stack.pollLast();
                } else {
                    return false;
                }
            }
        }
    }
    return stack.isEmpty();
  }
}
//time: O(n), space: O(n)