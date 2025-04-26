/* Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

 

Example 1:


Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
Example 2:


Input: root = [2,1,3]
Output: [2,1,3]         */

class Solution {
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);

        return getTree(list, 0, list.size() - 1);
    }

    private TreeNode getTree(List<TreeNode> list, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;

        TreeNode root = list.get(mid);
        root.left = getTree(list, start, mid - 1);
        root.right = getTree(list, mid + 1, end);

        return root;
    }

    private void inorder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }
}