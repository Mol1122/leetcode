/**
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        //time: O(n), space: O(n)
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            results.add(node.val);
            
            if (node.right != null) {
                stack.offerLast(node.right);
            }
            if (node.left != null) {
                stack.offerLast(node.left);
            }
        }
        return results;
        
        ////Morris: time: O(n), space: O(1)
//         List<Integer> results = new ArrayList<>();
//         if (root == null) {
//             return results;
//         }
//         TreeNode curr = null;
        
//         while (root != null) {
//             if (root.left != null) {
//                 curr = root.left;
                
//                 while (curr.right != null && curr.right != root) {
//                     curr = curr.right;
//                 }
//                 if (curr.right == root) {
//                     curr.right = null;
//                     root = root.right;
//                 } else {
//                     results.add(root.val);
//                     curr.right = root;
//                     root = root.left;
//                 }
//             } else {
//                 results.add(root.val);
//                 root = root.right;
//             }
//         }
//         return results;
    }
}