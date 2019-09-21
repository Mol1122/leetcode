/* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree
 

as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself. */

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        List<String> list = new ArrayList<>();
        serializeHelper(root, list);
        return String.join(",", list);
    }
    
    private void serializeHelper(Node root, List<String> list) {
        if (root == null) {
            return;
        }
        list.add(root.val + "");
        list.add(root.children.size() + "");
        
        for (int i = 0; i < root.children.size(); i++) {
            serializeHelper(root.children.get(i), list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        String[] strs = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(strs));
        return deserializeHelper(queue);
    }
    
    private Node deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        Node root = new Node(Integer.parseInt(queue.poll()), new ArrayList<>());
        int size = Integer.parseInt(queue.poll());
        
        for (int i = 0; i < size; i++) {
            root.children.add(deserializeHelper(queue));
        }
        return root;
    }
}

//time: O(n), space: O(n)