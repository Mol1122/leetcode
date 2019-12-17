//finding the maximum length of increasing path of a Binary Tree

class Solution {
	public int longestIncreasingBT(TreeNode root) {
        int[] max = {0};
        longestIncreasingBTHelper(root, max);
        return max[0];
    }

    private ResultType longestIncreasingBTHelper(TreeNode root, int[] max) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType left = longestIncreasingBTHelper(root.left, max);
        ResultType right = longestIncreasingBTHelper(root.right, max);

        int increasing = 1, decreasing = 1;
        if (root.left != null) {
            if (root.val > root.left.val) {
                decreasing = Math.max(decreasing, left.decreasing + 1);
            } else {
                increasing = Math.max(increasing, left.increasing + 1);
            }
        }
        if (root.right != null) {
            if (root.val > root.right.val) {
                decreasing = Math.max(decreasing, right.decreasing + 1);
            } else {
                increasing = Math.max(increasing, right.increasing + 1);
            }
        }
        max[0] = Math.max(max[0], increasing + decreasing - 1);
        return new ResultType(increasing, decreasing);
    }
}

class ResultType {
    int increasing, decreasing;
    public ResultType(int increasing, int decreasing) {
        this.increasing = increasing;
        this.decreasing = decreasing;
    }
}
//time: O(n), space: O(height)