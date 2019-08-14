/**
Get the list of list of keys in a given binary tree layer by layer. Each layer is represented by a list of keys and the keys are traversed from left to right.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [ [5], [3, 8], [1, 4, 11] ]

Corner Cases

What if the binary tree is null? Return an empty list of list in this case.  */
    
public class Solution {
  public List<List<Integer>> layerByLayer(TreeNode root) {
      List<List<Integer>> results = new ArrayList<>();
      if (root == null) {
          return results;
      }
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
    
      while (!queue.isEmpty()) {
          int size = queue.size();
          List<Integer> list = new ArrayList<>();
          for (int i = 0; i < size; i++) {
              TreeNode node = queue.poll();
              list.add(node.key);
              if (node.left != null) {
                  queue.offer(node.left);
              }
              if (node.right != null) {
                  queue.offer(node.right);
              }
          }
          results.add(list);
      }
      return results;
  }
}

//time: O(n), space: O(n)