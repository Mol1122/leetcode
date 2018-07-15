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
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                sb.append("X ");
                continue;
            }
            sb.append(curr.val + " ");
            queue.offer(curr.left);
            queue.offer(curr.right);
        }
        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        String[] strs = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        queue.offer(root);
        
        for (int i = 1; i < strs.length; i += 2) {
            TreeNode parent = queue.poll();

            if (!strs[i].equals("X")) {
                TreeNode left = new TreeNode(Integer.parseInt(strs[i]));
                parent.left = left;
                queue.offer(left);
            } 
            if (!strs[i + 1].equals("X")) {
                TreeNode right = new TreeNode(Integer.parseInt(strs[i + 1]));
                parent.right = right;
                queue.offer(right);
            } 
            
        }
        return root;
    }
}

/* 算法：BFS
** 难点：别忘了把新的node放到queue里
** 时间复杂度：O(n) */