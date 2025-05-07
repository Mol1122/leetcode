/* We run a preorder depth-first search (DFS) on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.

If a node has only one child, that child is guaranteed to be the left child.

Given the output traversal of this traversal, recover the tree and return its root.

 

Example 1:


Input: traversal = "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]
Example 2:


Input: traversal = "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]
Example 3:


Input: traversal = "1-401--349---90--88"
Output: [1,401,null,349,88,90]
 */

 class Solution {
    int index = 0;

    public TreeNode recoverFromPreorder(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        return construct(s, 0);
    }

    private TreeNode construct(String s, int depth) {
        if (index >= s.length()) {
            return null;
        }
        int dashCount = 0;
        while ((index + dashCount) < s.length() && s.charAt(index + dashCount) == '-') {
            dashCount++;
        }
        if (dashCount != depth) {
            return null;
        }
        index += dashCount;
        int num = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            num = num * 10 + s.charAt(index) - '0';
            index++;
        }
        TreeNode root = new TreeNode(num);
        root.left = construct(s, depth + 1);
        root.right = construct(s, depth + 1);
        return root;
    }
}