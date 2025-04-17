/* Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.

The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).

 

Example 1:


Input: root = [5,2,-3]
Output: [2,-3,4]
Example 2:


Input: root = [5,2,-5]
Output: [2]                     */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Map<Integer, Integer> sum2count = new HashMap<>(); //sum -> freq
        Map<Integer, List<Integer>> count2values = new HashMap<>(); //freq -> sum list
        helper(root, sum2count, count2values);

        int maxCount = 0;
        for (int key : sum2count.keySet()) {
            maxCount = Math.max(maxCount, sum2count.get(key));
        }
        int[] result = new int[count2values.get(maxCount).size()];
        int index = 0;
        for (int sum : count2values.get(maxCount)) {
            result[index++] = sum;
        }
        return result;
    }

    private int helper(TreeNode root, Map<Integer, Integer> sum2count, Map<Integer, List<Integer>> count2values) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, sum2count, count2values);
        int right = helper(root.right, sum2count, count2values);

        int sum = left + right + root.val;
        sum2count.putIfAbsent(sum, 0);
        sum2count.put(sum, sum2count.get(sum) + 1);
        count2values.putIfAbsent(sum2count.get(sum), new ArrayList<>());
        count2values.get(sum2count.get(sum)).add(sum);
        return sum;
    }
}
//time: O(n), space: O(height)