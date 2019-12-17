//get diameter of a general tree
public int getDiameter(TreeNode root) {
	if (root == null) {
		return 0;
	}
	int[] max = {0};
	dfs(root, max);
	return max[0];
}

private int dfs(TreeNode root, int[] max) {
	int a = Integer.MIN_VALUE;
	int b = Integer.MIN_VALUE;

	for (TreeNode child : root.children) {
		int temp = dfs(child, max);
		if (temp > a) {
			b = a;
			a = temp;
		} else if (temp > b) {
			b = temp;
		}
	}
	if (b >= 0) {
		max[0] = Math.max(max[0], a + b + 2);
		return a + 1;
	} else if (a >= 0) {
		max[0] = Math.max(max[0], a + 1);
		return a + 1;
	}
	return 0;
}

//time: O(n), space: O(height)