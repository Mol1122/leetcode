/**
Give a binary tree, and a target number, find all path that the sum of nodes equal to target, the path could be start and end at any node in the tree.

Each node contains a reference (pointer) to its parent node, ParentTreeNode is defined as follows:

class ParentTreeNode {
    int val;
    ParentTreeNode left;
    ParentTreeNode right;
    ParentTreeNode parent;
}
LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


Example
Example1

Input: {1,2,3,4},6
Output: [[2, 4],[2, 1, 3],[3, 1, 2],[4, 2]]
Explanation:
The tree is look like this:
    1
   / \
  2   3
 /
4
Example2

Input: {1,2,3,4},3
Output: [[1,2],[2,1],[3]]
Explanation:
The tree is look like this:
    1
   / \
  2   3
 /
4

 */

//Method 1
public class Solution {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(root, target, results);
        return results;
    }
    
    private void dfs(ParentTreeNode root, int target, List<List<Integer>> results) {
        if (root == null) {
            return;
        }
        List<Integer> path = new ArrayList<>();
        findSum(root, null, target, path, results);
        
        dfs(root.left, target, results);
        dfs(root.right, target, results);
    }
    
    private void findSum(ParentTreeNode root, ParentTreeNode father, int target, List<Integer> path, List<List<Integer>> results) {
        path.add(root.val);
        target -= root.val;
        
        if (target == 0) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            Collections.addAll(tmp,  new  Integer[path.size()]); 
            Collections.copy(tmp, path); 
            results.add(tmp);
        }
        if (root.parent != null && root.parent != father) {
            findSum(root.parent, root, target, path, results);
        }
        if (root.left != null && root.left != father) {
            findSum(root.left, root, target, path, results);
        }
        if (root.right != null && root.right != father) {
            findSum(root.right, root, target, path, results);
        }
        path.remove(path.size() - 1);
    }
}

//Method 2
/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public int val;
 *     public ParentTreeNode parent, left, right;
 * }
 */


public class Solution {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        List<ParentTreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);

        for (ParentTreeNode node : nodes) {
            Set<ParentTreeNode> set = new HashSet<>();
            getPath(node, 0, target, new ArrayList<>(), set, results);
        }
        return results;
    }

    private void getPath(ParentTreeNode root, int sum, int target, List<Integer> path, 
                         Set<ParentTreeNode> set, List<List<Integer>> results) {
        if (root == null || set.contains(root)) {
            return;
        }
        sum += root.val;
        path.add(root.val);
        set.add(root);

        if (sum == target) {
            results.add(new ArrayList<>(path));
        }

        getPath(root.left, sum, target, path, set, results);
        getPath(root.right, sum, target, path, set, results);
        getPath(root.parent, sum, target, path, set, results);
        path.remove(path.size() - 1);
        set.remove(root);
    }

    private void inorder(ParentTreeNode root, List<ParentTreeNode> nodes) {
        if (root == null) {
            return;
        }
        inorder(root.left, nodes);
        nodes.add(root);
        inorder(root.right, nodes);
    }
}