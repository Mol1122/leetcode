class Solution {
    public int longestIncreasingBST(TreeNode root) {
        int[] max = {0};
        longestIncreasingBSTHelper(root, max);
        return max[0];
    }

    private int[] longestIncreasingBSTHelper(TreeNode root, int[] max) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = longestIncreasingBSTHelper(root.left, max);
        int[] right = longestIncreasingBSTHelper(root.right, max);

        max[0] = Math.max(max[0], left[0] + right[1] + 1);

        return new int[]{left[0] + 1, right[1] + 1};
    }
}
//time: O(n), space: O(height)