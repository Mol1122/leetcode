/* You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

Create a root node whose value is the maximum value in nums.
Recursively build the left subtree on the subarray prefix to the left of the maximum value.
Recursively build the right subtree on the subarray suffix to the right of the maximum value.
Return the maximum binary tree built from nums.

 

Example 1:


Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]
Explanation: The recursive calls are as follow:
- The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
    - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
        - Empty array, so no child.
        - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
    - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.
Example 2:


Input: nums = [3,2,1]
Output: [3,null,2,null,1] */


class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = getMap(nums);
        return construct(nums, 0, nums.length - 1, map);
    }

    private TreeNode construct(int[] nums, int start, int end, Map<Integer, Integer> map) {
        if (start > end) {
            return null;
        }
        int max = getMax(nums, start, end);
        TreeNode root = new TreeNode(max);
        int maxIndex = map.get(max);
        
        root.left = construct(nums, start, maxIndex - 1, map);
        root.right = construct(nums, maxIndex + 1, end, map);
        return root;
    }

    private Map<Integer, Integer> getMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        return map;
    }

    private int getMax(int[] nums, int start, int end) {
        int max = nums[start];

        for (int i = start; i <= end; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }
}