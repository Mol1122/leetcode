//112. Path Sum https://leetcode.com/problems/path-sum/
/* Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
Note: A leaf is a node with no children. 
Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1*/

//Path Sum I
class Solution {
    boolean canGet = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        dfs(root, sum);
        return canGet;
    }
    
    private void dfs(TreeNode root, int sum) {
        sum -= root.val;
        //如果是一条line, 不希望把短的那边看成leaf, 那么leaf就需要特殊判断了
        if (sum == 0 && root.left == null && root.right == null) {
            canGet = true;
            return;
        }
        if (root.left != null) {
            dfs(root.left, sum);
        }
        if (root.right != null) {
            dfs(root.right, sum);
        }
    }
}

/* 113. Path Sum II  https://leetcode.com/problems/path-sum-ii/
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
Note: A leaf is a node with no children. 
    Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]*/

//Path Sum II
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        dfs(root, sum, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> results) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        sum -= root.val;
        
        if (sum == 0 && root.left == null && root.right == null) {
            results.add(new ArrayList<>(path));
            path.remove(path.size() - 1); //走完左边要把左儿子remove掉
            return;
        }
        dfs(root.left, sum, path, results);
        dfs(root.right, sum, path, results);
        path.remove(path.size() - 1); //走完右边要把右儿子remove掉，什么时候要remove是根据树的traverse来决定的
    }
}

/* 时间复杂度：O(n) */

/* 437. Path Sum III https://leetcode.com/problems/path-sum-iii/
You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11 */

//Path Sum III 
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum); //算当前节点，不算当前节点，不算当前节点
    }
    
    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, sum - root.val); //左边有多少path sum to target
        int right = dfs(root.right, sum - root.val); //右边有多少path sum to target
        if (root.val == sum) { //判断到当前node要不要算一个path that sum to target
            return left + right + 1;
        } else {
            return left + right;
        }
    }
}

/* 算法：典型的recursion
** 难点：如果实现不一定从root开始， 并且leftPath和rightPath不能舍弃一个 
** 时间复杂度:O(n) */

/* 246. Binary Tree Path Sum II  https://www.lintcode.com/problem/binary-tree-path-sum-ii/description?_from=ladder&&fromId=1
Your are given a binary tree in which each node contains a value. Design an algorithm to get all paths which sum to a given value. 
The path does not need to start or end at the root or a leaf, but it must go in a straight line down.

Example
Given a binary tree:

    1
   / \
  2   3
 /   /
4   2
for target = 6, return

[
  [2, 4],
  [1, 3, 2]
] */
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

//Binary Tree Path Sum II, 求所有sum等于target的路径，不一定要从root开始或leaf结束，但是一定是straight line down
public class Solution {
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        dfs(root, target, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(TreeNode root, int target, List<Integer> path, List<List<Integer>> results) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        int sum = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            sum += path.get(i);
            if (sum == target) {
                results.add(new ArrayList<>(path.subList(i, path.size())));
            }
        }
        dfs(root.left, target, path, results);
        dfs(root.right, target, path, results);
        path.remove(path.size() - 1);
    }
}

/* 472. Binary Tree Path Sum III  https://www.lintcode.com/problem/binary-tree-path-sum-iii/description?_from=ladder&&fromId=1
Give a binary tree, and a target number, find all path that the sum of nodes equal to target, 
the path could be start and end at any node in the tree.

Example
Given binary tree:

    1
   / \
  2   3
 /
4
and target = 6. Return :

[
  [2, 4],
  [2, 1, 3],
  [3, 1, 2],
  [4, 2]
] */

/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public int val;
 *     public ParentTreeNode parent, left, right;
 * }
 */

//Binary Tree Path Sum III
public class Solution {
    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        List<ParentTreeNode> list = new ArrayList<>();
        inorder(root, list);
        
        for (ParentTreeNode node : list) {
            Set<ParentTreeNode> set = new HashSet<>();
            dfs(node, target, set, new ArrayList<>(), results);
        }
        return results;
    }
    
    private void dfs(ParentTreeNode root, int target, Set<ParentTreeNode> set, 
                     List<Integer> path, List<List<Integer>> results) {
        if (root == null || set.contains(root)) {
            return;
        }          
        set.add(root);
        path.add(root.val);
        target -= root.val;
        
        if (target == 0) {
            results.add(new ArrayList<>(path));
        }
        dfs(root.left, target, set, path, results);
        dfs(root.right, target, set, path, results);
        dfs(root.parent, target, set, path, results);
        
        set.remove(root);
        path.remove(path.size() - 1);
        target += root.val;
    }
    
    private void inorder(ParentTreeNode root, List<ParentTreeNode> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }
}

/* 这个题的本质就是在一个图里面找到所有路径之和为target，树也算图的一种。
先把所有node存进list，然后依次把这些node作为起始点，开始dfs，这样就可以套用backtracking的模板。
老师的做法也相当于先序遍历，然后再dfs */



