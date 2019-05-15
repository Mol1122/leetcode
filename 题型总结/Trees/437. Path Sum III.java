/**
 You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int[] count = {0};
        getPath(root, sum, new ArrayList<>(), count);
        return count[0];
    }
    
    private void getPath(TreeNode root, int sum, List<Integer> path, int[] count) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        int temp = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            temp += path.get(i);
            if (temp == sum) {
                count[0]++;
            }
        }
        getPath(root.left, sum, path, count);
        getPath(root.right, sum, path, count);
        path.remove(path.size() - 1);
    }
}
//time: O(n), space: O(height)