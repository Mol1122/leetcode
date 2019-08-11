/* Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef". */

class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Deque<Object> stack = new ArrayDeque<>();
        int number = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } else if (c == '[') {
                stack.offerLast(number);
                number = 0;
            } else if (c == ']') {
                String str = popStack(stack);
                int count = (Integer)stack.pollLast();
                for (int i = 0; i < count; i++) {
                    stack.offerLast(str);
                }
            } else {
                stack.offerLast(String.valueOf(c));
            }
        }
        return popStack(stack);
    }
    
    private String popStack(Deque<Object> stack) {
        Deque<String> temp = new ArrayDeque<>();
        while (!stack.isEmpty() && stack.peekLast() instanceof String) {
            temp.offerLast((String)stack.pollLast());
        } 
        StringBuilder sb = new StringBuilder();
        while (!temp.isEmpty()) {
            sb.append(temp.pollLast());
        }
        return sb.toString();
    }
}
//time: O(n), space: O(n)