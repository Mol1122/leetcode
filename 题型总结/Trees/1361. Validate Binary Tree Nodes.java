/* You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.

 

Example 1:


Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true
Example 2:


Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false
Example 3:


Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false
 */

 class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        if (n < 0 || leftChild == null || rightChild == null || leftChild.length != rightChild.length) {
            return false;
        }
        int root = findRoot(n, leftChild, rightChild);
        if (root == -1) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(root);
        boolean result = validate(leftChild, rightChild, root, visited);
        if (!result) {
            return false;
        }
        return visited.size() == n;
    }

    private boolean validate(int[] leftChild, int[] rightChild, int curr, Set<Integer> visited) {
        if (curr == -1) {
            return true;
        }
        if (leftChild[curr] != -1 && visited.contains(leftChild[curr])) {
            return false;
        } 
        if (rightChild[curr] != -1 && visited.contains(rightChild[curr])) {
            return false;
        }
        if (leftChild[curr] != -1) {
            visited.add(leftChild[curr]);
        }
        if (rightChild[curr] != -1) {
            visited.add(rightChild[curr]);
        }
        return validate(leftChild, rightChild, leftChild[curr], visited) &&
               validate(leftChild, rightChild, rightChild[curr], visited);
    }

    private int findRoot(int n, int[] leftChild, int[] rightChild) {
        Set<Integer> set = new HashSet<>();

        for (int child : leftChild) {
            set.add(child);
        }
        for (int child : rightChild) {
            set.add(child);
        }
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }
}