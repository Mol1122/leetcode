/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        //BFS
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder str = new StringBuilder();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                str.append("X ");
                continue;
            }
            str.append(node.val + " ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return str.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
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

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
/* 算法：用BFS去serialize，按顺序deserilize. 用queue来记录走到的当前node */