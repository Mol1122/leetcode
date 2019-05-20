/* Max Path Sum Binary Tree (path from leaf to root) */
public class Solution {
	public int maxPath(TreeNode root) {
		int[] max = {Integer.MIN_VALUE};
		getPath(root, 0, max);
		return max[0];
	}

	private void getPath(TreeNode root, int prefixSum, int[] max) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			max[0] = Math.max(max[0], prefixSum + root.val);
			return;
		}
		getPath(root.left, prefixSum + root.val, max);
		getPath(root.right, prefixSum + root.val, max);
	}
}
//time: O(n), space: O(height)