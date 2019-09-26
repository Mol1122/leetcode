/**
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or 
two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis 
contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5     */

class Solution {
    int index = 0;
    
    public TreeNode str2tree(String s) {
        int len = s.length();
        if (len == 0 || index >= len) {
            return null;
        }
        int sign = 1, key = 0;
        if (s.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
            key = key * 10 + s.charAt(index) - '0';
            index++;
        }
        TreeNode root = new TreeNode(sign * key);
        if (index >= len || s.charAt(index) == ')') {
            index++;
            return root;
        }
        index++;
        root.left = str2tree(s);
        if (index >= len || s.charAt(index) == ')') {
            index++;
            return root;
        }
        index++;
        root.right = str2tree(s);
        if (index >= len || s.charAt(index) == ')') {
            index++;
            return root;
        }
        return root;
    }
}
//time: O(n), space: O(height)