public static boolean preOrder(TreeNode root) {
        return helper(root, null, null);
}
private static boolean helper(TreeNode root, TreeNode minNode, TreeNode maxNode) {
        if (root == null) {
            return true;
        }
        if ((minNode != null && minNode.val > root.val) || (maxNode != null && maxNode.val <= root.val)) {
            return false;
        }
        return helper(root.left, minNode, root) && helper(root.right, root, maxNode);
}