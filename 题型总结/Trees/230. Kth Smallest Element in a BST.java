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
    //时间复杂度 O(n) 最好最坏都是。
    //算法思想类似于 Quick Select。
    //这个算法的好处是，如果多次查询的话，给每个节点统计儿子个数这个过程只需要做一次。查询可以很快。
    
    public int kthSmallest(TreeNode root, int k) {
        Map<TreeNode, Integer> numOfChildren = new HashMap<>();
        countChildren(root, numOfChildren);
        
        return quickSelect(root, k, numOfChildren);
    }
    
    private int countChildren(TreeNode root, Map<TreeNode, Integer> numOfChildren) {
        if (root == null) {
            return 0;
        }
        int left = countChildren(root.left, numOfChildren);
        int right = countChildren(root.right, numOfChildren);
        numOfChildren.put(root, left + right + 1);
        return left + right + 1;
     }
    
    private int quickSelect(TreeNode root, int k, Map<TreeNode, Integer> numOfChildren) {
        if (root == null) {
            return -1;
        }
        int left = root.left == null ? 0 : numOfChildren.get(root.left);
        if (left >= k) {
            return quickSelect(root.left, k, numOfChildren);
        }
        if (left + 1 == k) {
            return root.val;
        }
        return quickSelect(root.right, k - left - 1, numOfChildren);
    }
    
    
//     int index, kth;
//     public int kthSmallest(TreeNode root, int k) {
//         if (root == null) {
//             return -1;
//         }
//         inorder(root, k);
//         return kth;
//     }
    
//     private void inorder(TreeNode root, int k) {
//         if (root == null) {
//             return;
//         }
//         inorder(root.left, k);
//         index++;
//         if (index == k) {
//             kth = root.val;
//         }
//         if (index < k) {
//             inorder(root.right, k);
//         }
//     }
    
}

/* 算法：inorder traversal. 因为在BST中，inorder是排好序的。
** 时间复杂度：O(n) 
** Follow up: 如果这个函数会被调用很多次该怎么办？ 可以用一个 HashMap<TreeNode, Integer> 来存储某个节点为代表的子树的节点个数
**             kthSmallest 的实现中用类似 Quick Select 的算法去找到 kth smallest element
**             时间复杂度为 O(h)，h 为树的高度。*/