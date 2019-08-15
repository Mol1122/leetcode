/**
Diagonal sum in a binary tree is the sum of all the node’s data lying through the dashed lines. 
Given a Binary Tree, print all diagonal sums.

For the above input tree, output should be:

{ 11, 14, 5 }  */

public class Solution {
  public List<Integer> diagonalSum(TreeNode root) {
    List<Integer> results = new ArrayList<>();
    if (root == null) {
      return results;
    }
    Map<Integer, Integer> vertical2sum = new HashMap<>();
    Queue<TreeNode> queue = new LinkedList<>();
    Queue<Integer> verticalDist = new LinkedList<>();
    queue.offer(root);
    verticalDist.offer(0);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      int vertical = verticalDist.poll();

      while (node != null) { //把右侧的node sum都算上
        vertical2sum.putIfAbsent(vertical, 0);
        vertical2sum.put(vertical, vertical2sum.get(vertical) + node.key);
        if (node.left != null) {
          queue.offer(node.left);
          verticalDist.offer(vertical + 1);
        }
        node = node.right;
      }
    }
    for (int key : vertical2sum.keySet()) {
      results.add(vertical2sum.get(key));
    }
    return results;
  }
}
//难点：记录当前node到经过root的diagonal line的vertical distance
//time: O(n), space: O(n)