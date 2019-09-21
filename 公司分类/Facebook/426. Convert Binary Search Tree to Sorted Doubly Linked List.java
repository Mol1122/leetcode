/* Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right 
pointers as synonymous to the previous and next pointers in a doubly-linked list. */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    Node prev = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node(-1, null, null);
        prev = dummy;
        helper(root);
        
        dummy.right.left = prev;
        prev.right = dummy.right;
        return dummy.right;
    }
    
    private void helper(Node root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;
        helper(root.right);
    }
}
//time: O(n), space: O(height)