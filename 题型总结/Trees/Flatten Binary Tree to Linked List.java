/* Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
 */ 

class Solution {
    //TreeNode lastNode = null;
    public void flatten(TreeNode root) {
        //traverse, pre-order
        // if (root == null) {
        //     return;
        // }
        // //更改当前的node
        // if (lastNode != null) {
        //     lastNode.left = null;
        //     lastNode.right = root;
        // }
        // //前序遍历
        // lastNode = root;
        // TreeNode right = root.right; //难点
        // flatten(root.left);
        // flatten(right);
        
        //divided conquer, 总是后序遍历的思想
       helper(root);
    }
    
   private TreeNode helper(TreeNode root) {
       if (root == null) {
           return null;
       }
       TreeNode left = helper(root.left);
       TreeNode right = helper(root.right);
       
       if (left != null) {
           left.right = root.right;
           root.right = root.left;
           root.left = null;
       }
       if (right != null) {
           return right;
       }
       if (left != null) {
           return left;
       }
       return root;
   }
}
//time: O(n), space: O(height)