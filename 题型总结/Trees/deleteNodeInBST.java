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
		root.right.left = root.left;
		return root.right
	}

	TreeNode smallest = deleteSmallest(root.right);
	smallest.left = root.left;
	smallest.right = root.right;

	return smallest;
}

private TreeNode deleteSmallest(TreeNode curr) {
	TreeNode prev = curr;
	curr = curr.left;

	while (curr.left != null) {
		prev = curr;
		curr = curr.left;
	}
	//curr is the smallest one, and prev is its parent
	prev.left = prev.left.right;
	return curr;
}