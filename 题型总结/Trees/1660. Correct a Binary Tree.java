/* You have a binary tree with a small defect. There is exactly one invalid node where its right child incorrectly points to another node at the same depth but to the invalid node's right.

Given the root of the binary tree with this defect, root, return the root of the binary tree after removing this invalid node and every node underneath it (minus the node it incorrectly points to).

Custom testing:

The test input is read as 3 lines:

TreeNode root
int fromNode (not available to correctBinaryTree)
int toNode (not available to correctBinaryTree)
After the binary tree rooted at root is parsed, the TreeNode with value of fromNode will have its right child pointer pointing to the TreeNode with a value of toNode. Then, root is passed to correctBinaryTree.

 

Example 1:



Input: root = [1,2,3], fromNode = 2, toNode = 3
Output: [1,null,3]
Explanation: The node with value 2 is invalid, so remove it.
Example 2:



Input: root = [8,3,1,7,null,9,4,2,null,null,null,5,6], fromNode = 7, toNode = 4
Output: [8,3,1,null,null,9,4,null,null,5,6]
Explanation: The node with value 7 is invalid, so remove it and the node underneath it, node 2.
 */

 //Method 1
 class Solution {
    public TreeNode correctBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<TreeNode, TreeNode> node2parent = new HashMap<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                    node2parent.put(node.left, node);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    node2parent.put(node.right, node);
                }
            }
            for (TreeNode node : node2parent.keySet()) {
                if (node2parent.containsKey(node.right)) {
                    TreeNode parent = node2parent.get(node);
                    if (parent.left == node) {
                        parent.left = null;
                        node.left = null;
                        node.right = null;
                    } else if (parent.right == node) {
                        parent.right = null;
                        node.right = null;
                        node.left = null;
                    }
                }
            }
        }
        return root;
    }
}

//Method 2
class Solution {
    public TreeNode correctBinaryTree(TreeNode root) {
        Set<TreeNode> visited = new HashSet<>();
        return getTree(root, visited);
    }

    private TreeNode getTree(TreeNode root, Set<TreeNode> visited) {
        if (root == null) {
            return null;
        }
        if (visited.contains(root.right)) {
            return null;
        }
        visited.add(root);
        root.right = getTree(root.right, visited);
        root.left = getTree(root.left, visited);
        return root;
    }
}
