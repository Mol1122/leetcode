/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public TreeNode reconstruct(int[] level) {
      if (level == null || level.length == 0) {
          return null;
      }
      int index = 0;
      Queue<Result> queue = new LinkedList<>();
      TreeNode root = new TreeNode(level[index++]);
      queue.offer(new Result(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    
      while (!queue.isEmpty()) {
          Result rt = queue.poll();
          
          if (index < level.length && level[index] > rt.min && level[index] < rt.node.key) {
              TreeNode temp = new TreeNode(level[index++]);
              rt.node.left = temp;
            
              queue.offer(new Result(temp, rt.min, rt.node.key));
          }
          if (index < level.length && level[index] > rt.node.key && level[index] < rt.max) {
              TreeNode temp = new TreeNode(level[index++]);
              rt.node.right = temp;
            
              queue.offer(new Result(temp, rt.node.key, rt.max));
          }
      }
      return root;
  }
}

class Result {
    TreeNode node;
    int min, max;
  
    public Result(TreeNode node, int min, int max) {
        this.node = node;
        this.min = min;
        this.max = max;
    }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */
