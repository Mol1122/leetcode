/* A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.

Return a deep copy of the tree.

The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (in the input) where the random pointer points to, or null if it does not point to any node. */

/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    public NodeCopy copyRandomBinaryTree(Node root) {
        if (root == null) {
            return null;
        }
        Map<Node, NodeCopy> map = new HashMap<>();
        dfs(root, map);
        connect(root, map);
        
        return map.get(root);
    }
    
    private void dfs(Node root, Map<Node, NodeCopy> map) {
        if (root == null) {
            return;
        }
        map.put(root, new NodeCopy(root.val));
        dfs(root.left, map);
        dfs(root.right, map);
    }
    
    private void connect(Node root, Map<Node, NodeCopy> map) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            map.get(root).left = map.get(root.left);
        }
        if (root.right != null) {
            map.get(root).right = map.get(root.right);
        }
        if (root.random != null) {
            map.get(root).random = map.get(root.random);
        }
        connect(root.left, map);
        connect(root.right, map);
    }
}