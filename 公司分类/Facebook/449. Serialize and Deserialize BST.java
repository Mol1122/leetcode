/**
Serialization is the process of converting a data structure or object into a sequence of 
bits so that it can be stored in a file or memory buffer, or transmitted across a 
network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no 
restriction on how your serialization/deserialization algorithm should work. You 
just need to ensure that a binary search tree can be serialized to a string and this 
string can be deserialized to the original tree structure.

The encoded string should be as compact as possible. */

public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("X ");
                continue;
            }
            sb.append(node.val + " ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return sb.toString();
    }


    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        String[] strs = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        for (int i = 1; i < strs.length; i += 2) {
            TreeNode node = queue.poll();
            if (!strs[i].equals("X")) {
                TreeNode left = new TreeNode(Integer.parseInt(strs[i]));
                node.left = left;
                queue.offer(left);
            }
            if (!strs[i + 1].equals("X")) {
                TreeNode right = new TreeNode(Integer.parseInt(strs[i + 1]));
                node.right = right;
                queue.offer(right);
            }
        }
        return root;
    }
}
