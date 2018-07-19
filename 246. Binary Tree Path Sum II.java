/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) {
            return results;
        }
        helper(root, target, 0, path, results);
        return results;
    }
    
    private void helper(TreeNode root, int target, int level, List<Integer> path, List<List<Integer>> results) {
        if (root == null) {
            return;
        }
        int tempSum = target;
        path.add(root.val);
        for (int i = level; i >= 0; i--) {
            tempSum -= path.get(i);
            if (tempSum == 0) {
                List<Integer> temp = new ArrayList<>();
                for (int j = i; j <= level; j++) {
                    temp.add(path.get(j));
                }
                results.add(temp);
            }
        }
        System.out.println("Before:");
        for (int i : path) {
            System.out.print(i + " ");
        }
        System.out.println();
        helper(root.left, target, level + 1, path, results);
        helper(root.right, target, level + 1, path, results);
        path.remove(path.size() - 1);
    }
}
/* 算法：有点类似于搜索类的DFS
** 难点：最后52行一定要减去最后一个数。因为算法是先往左走到最底层，每走到新的一层就加上root,然后根据path看看能不能组合成sum. 当左右两边都走完的时候要减掉root (path.remove(path.size() - 1), 表示这一层的左右两边都走过了。 这样走到右子树的时候不会包含左子树的值 */