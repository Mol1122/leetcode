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
        StringBuilder sb = new StringBuilder();
        if (root == null) return "null";
        
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.empty()) {
            root = st.pop();
            sb.append(root.val).append(" ");
            if (root.right != null) st.push(root.right);
            if (root.left != null) st.push(root.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null")) {
            return null;
        }
        Queue<Integer> queue = new LinkedList<>();
        String[] strs = data.split(" ");
        for (String s : strs) {
            queue.offer(Integer.parseInt(s));
        }
        return getNode(queue);
    }
    private TreeNode getNode(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(queue.poll());
        Queue<Integer> small = new LinkedList<>();
        while (!queue.isEmpty() && queue.peek() < root.val) {
            small.offer(queue.poll());
        }
        root.left = getNode(small);
        root.right = getNode(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

/* 算法：利用stack实现preorder. 利用BST性质去recursively build the tree- data里，所有小于root的都在root的左边，大于的在root右边 */