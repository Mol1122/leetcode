/* Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid. */


//Method 1 With Stack
class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Stack<Integer> stack = new Stack<>(); //index of '('
        Set<Integer> index2remove = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    index2remove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            index2remove.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!index2remove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
//time: O(n), space: O(n)

//Method 2 Without Stack. If java support replace a char at index, then space could reduce to O(1)
class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] sc = s.toCharArray();
        int count = 0;
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == '(') {
                count++;
            } else if (sc[i] == ')') {
                if (count > 0) {
                    count--;
                } else {
                    sc[i] = '#';
                }         
            }
        }
        count = 0;
        for (int i = sc.length - 1; i >= 0; i--) {
            if (sc[i] == ')') {
                count++;
            } else if (sc[i] == '(') {
                if (count > 0) {
                    count--;
                } else {
                    sc[i] = '#';
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : sc) {
            if (c != '#') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
//time: O(n), space: O(n)


//Method 3: space: O(1)
class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int leftCount = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                sb.append('(');
                leftCount++;
            } else if (c == ')') {
                if (leftCount > 0) {
                    sb.append(')');
                    leftCount--;
                } else {
                    continue;
                }
            } else {
                sb.append(c);
            }
        }
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (leftCount == 0) {
                return sb.toString();
            }
            if (sb.charAt(i) == '(') {
                sb.deleteCharAt(i);
                leftCount--;
            }
        }
        return sb.toString();
    }
}
//time: O(n), space: O(1)