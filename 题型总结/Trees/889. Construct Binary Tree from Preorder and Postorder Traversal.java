/* Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7] */

class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post == null || pre.length != post.length) {
            return null;
        }   
        return construct(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }
    
    private TreeNode construct(int[] pre, int preLeft, int preRight, int[] post, int postLeft, int postRight) {
        if (preLeft > preRight || postLeft > postRight) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        int leftLength = 0;
        for (int i = postLeft; i <= postRight - 1; i++) {
            if (post[i] == pre[preLeft + 1]) {
                leftLength = i - postLeft + 1;
                break;
            }
        }
        root.left = construct(pre, preLeft + 1, preLeft + leftLength, post, postLeft, postLeft + leftLength - 1);
        root.right = construct(pre, preLeft + leftLength + 1, preRight, post, postLeft + leftLength, postRight - 1);
        return root;
    }
}
//time: O(n), space: O(n)