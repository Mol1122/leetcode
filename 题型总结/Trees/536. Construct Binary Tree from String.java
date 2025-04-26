/* You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

 

Example 1:


Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
Example 2:

Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]
Example 3:

Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7] */

class Solution {
    int index = 0;

    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0 || index >= s.length()) {
            return null;
        }
        int sign = 1, num = 0;
        if (s.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            num = num * 10 + (s.charAt(index) - '0');
            index++;
        }
        TreeNode root = new TreeNode(sign * num);
        if (index >= s.length() || s.charAt(index) == ')') {
            index++;
            return root;
        }
        index++;
        root.left = str2tree(s);
        if (index >= s.length() || s.charAt(index) == ')') {
            index++;
            return root;
        }
        index++;
        root.right = str2tree(s);
        if (index >= s.length() || s.charAt(index) == ')') {
            index++;
            return root;
        }
        return root;
    }
}
//time: O(n), space: O(height)