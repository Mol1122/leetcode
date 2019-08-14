/**
Given a binary Tree and the two keys, determine whether the two nodes are cousins of each other or not. Two nodes are cousins of each other if they are at the same level and have different parents.

Assumptions:

It is not guranteed the two keys are all in the binary tree.
There are no duplicate keys in the binary tree.
Examples:

     6
   /   \
  3     5
 / \   / \

7   8 1   13
7 and 1, result is true.
3 and 5, result is false.
7 and 5, result is false.     */

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean x_exist = false, y_exist = false;
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == x) {
                    x_exist = true;
                }
                if (node.val == y) {
                    y_exist = true;
                }
                if (node.left != null && node.right != null) {
                    if (node.left.val == x && node.right.val == y) {
                        return false;
                    }
                    if (node.left.val == y && node.right.val == x) {
                        return false;
                    }
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (x_exist && y_exist) {
                return true;
            }
        }
        return false;
    }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */