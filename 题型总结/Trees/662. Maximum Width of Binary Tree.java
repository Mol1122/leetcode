/* Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.

 

Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Example 2:


Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
Example 3:


Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).       */

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<Pair> deque = new ArrayDeque<>();
        deque.offerLast(new Pair(root, 1));
        int maxWidth = 0;

        while (!deque.isEmpty()) {
            int size = deque.size();
            maxWidth = Math.max(maxWidth, deque.peekLast().index - deque.peekFirst().index + 1);

            for (int i = 0; i < size; i++) {
                Pair p = deque.pollFirst();
                if (p.node.left != null) {
                    deque.offerLast(new Pair(p.node.left, p.index * 2));
                }
                if (p.node.right != null) {
                    deque.offerLast(new Pair(p.node.right, p.index * 2 + 1));
                }
            }
        }
        return maxWidth;
    }
}

class Pair {
    TreeNode node;
    int index;

    public Pair(TreeNode node, int index) {
        this.node = node;
        this.index = index;
    }
}
//time: O(n), space: O(n)