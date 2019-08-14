/**
Given the levelorder traversal sequence of a binary search tree, reconstruct the original tree.

Assumptions

The given sequence is not null
There are no duplicate keys in the binary search tree
Examples

levelorder traversal = {5, 3, 8, 1, 4, 11}

the corresponding binary search tree is

        5

      /    \

    3        8

  /   \        \

1      4        11   */

public class Solution {
  public TreeNode reconstruct(int[] level) {
    if (level == null || level.length == 0) {
        return null;
    }
    int index = 0;
    Queue<Pair> queue = new LinkedList<>();
    TreeNode root = new TreeNode(level[index++]);
    queue.offer(new Pair(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

    while (!queue.isEmpty()) {
        Pair p = queue.poll();
        if (index < level.length && level[index] > p.min && level[index] < p.node.key) {
            p.node.left = new TreeNode(level[index++]);
            queue.offer(new Pair(p.node.left, p.min, p.node.key));
        }
        if (index < level.length && level[index] > p.node.key && level[index] < p.max) {
            p.node.right = new TreeNode(level[index++]);
            queue.offer(new Pair(p.node.right, p.node.key, p.max));
        }
    }
    return root;
  }
}

class Pair {
    TreeNode node;
    int min, max;

    public Pair(TreeNode node, int min, int max) {
        this.node = node;
        this.min = min;
        this.max = max;
    }
}

//time: O(n), space: O(n)