/**
Get the list of keys in a given binary tree layer by layer in zig-zag order.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [5, 3, 8, 11, 4, 1]  */

public class Solution {
  public List<Integer> zigZag(TreeNode root) {
      List<Integer> results = new ArrayList<>();
      if (root == null) {
          return results;
      }
      Deque<TreeNode> deque = new ArrayDeque<>();
      deque.offerLast(root);
      boolean flag = true;
    
      while (!deque.isEmpty()) {
          int size = deque.size();
          for (int i = 0; i < size; i++) {
              if (flag) {
                  TreeNode node = deque.pollLast();
                  results.add(node.key);
                  if (node.right != null) {
                      deque.offerFirst(node.right);
                  }
                  if (node.left != null) {
                      deque.offerFirst(node.left);
                  }
              } else {
                  TreeNode node = deque.pollFirst();
                  results.add(node.key);
                  if (node.left != null) {
                      deque.offerLast(node.left);
                  }
                  if (node.right != null) {
                      deque.offerLast(node.right);
                  }
              }
          }
          flag = !flag;
      }
      return results;
  }
}
//time: O(n), space: O(n)
//难点：一边poll就要从另外一边offer, 这个顺序不需要背，模拟一下就知道了
