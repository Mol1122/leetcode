public TreeNode delete(TreeNode root, int target) {
	if (root == null) {
		return null;
	}
	if (root.val > target) {
		root.left = delete(root.left, target);
		return root;
	} else if (root.val < target) {
		root.right = delete(root.right, target);
		return root;
	}

	if (root.left == null) {
		return root.right;
	} else if (root.right == null) {
		return root.left;
	}

	if (root.right.left == null) {
		
	}
}