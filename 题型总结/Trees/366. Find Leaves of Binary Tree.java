/* Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, 
repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []   */

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, List<Integer>> depth2nums = new HashMap<>();
        helper(root, depth2nums);
        
        for (int depth : depth2nums.keySet()) {
            results.add(depth2nums.get(depth));
        }
        return results;
    }
    
    private int helper(TreeNode root, Map<Integer, List<Integer>> depth2nums) {
        if (root == null) {
            return 0;
        }
        int height = Math.max(helper(root.left, depth2nums), helper(root.right, depth2nums)) + 1;
        depth2nums.putIfAbsent(height, new ArrayList<>());
        depth2nums.get(height).add(root.val);
        return height;
    }
}