class ResultType {
    int increasing, decreasing;
    public ResultType(int increasing, int decreasing) {
        this.increasing = increasing;
        this.decreasing = decreasing;
    }
}

static int max = 0;
    public static int longestIncreasingBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }

    private static void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        int length = 1;
        TreeNode curr = root;
        //find min
        while (curr.left != null) {
            length++;
            curr = curr.left;
        }
        //find max
        curr = root;
        while (curr.right != null) {
            length++;
            curr = curr.right;
        }
        max = Math.max(max, length);
        helper(root.left);
        helper(root.right);
    }

    //finding the maximum length of increasing path of a Binary Tree
    static int max1 = 0;
    public static int longestIncreasingBT(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper1(root);
        return max1;
    }

    private static ResultType helper1(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType left = helper1(root.left);
        ResultType right = helper1(root.right);

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
        max1 = Math.max(max1, increasing + decreasing - 1);
        return new ResultType(increasing, decreasing);
    }