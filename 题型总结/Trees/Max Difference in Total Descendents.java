/* Find the node with the max difference in the total number of descendents in its left subtree and right subtree */

public class {
	public TreeNode maxDiffNode(TreeNode root) {
		if (root == null) {
			return null;
		}
		int[] globalMax = Integer.MIN_VALUE;
		TreeNode[] result = null;
		getMax(root, globalMax, result);
		return result;
	}

	private int getMax(TreeNode root, int[] globalMax, TreeNode[] result) {
		if (root == null) {
			return 0;
		}
		int left = getMax(root.left, globalMax, result);
		int right = getMax(root.right, globalMax, result);
		if (Math.abs(left - right) > globalMax[0]) {
			globalMax[0] = Math.abs(left - right);
			result[0] = root;
		}
		return left + right + 1;
	}
}
//time: O(n), space: O(height)