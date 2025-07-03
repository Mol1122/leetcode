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

//Method 1
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

public class Solution {
    /**
     * @param a: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();

        for (int i = 0; i <= nums.length; i++) {
            TreeNode curr = i == nums.length ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(nums[i]);
            while (!stack.isEmpty() && curr.val > stack.peekLast().val) { //curr < prevNode means they are still the right children of prevNode
                TreeNode prevNode = stack.pollLast();
                if (stack.isEmpty()) {
                    curr.left = prevNode;
                } else {
                    TreeNode prevMax = stack.peekLast();
                    if (prevMax.val > curr.val) { //still don't know whether curr is the right child of prevMax
                        curr.left = prevNode;
                    } else {
                        prevMax.right = prevNode;
                    }
                }
            }
            stack.offerLast(curr);
        }
        return stack.peekLast().left; //stack.peekLast() is TreeNode(Integer.MAX_VALUE)
    }
}
//time: O(n), space: O(n)

/* 单调递减栈 */

//Method 2
public class Solution {
    /**
     * @param a: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return getMaxTree(nums, 0, nums.length - 1);
    }

    private TreeNode getMaxTree(int[] nums, int start, int end) {
        int maxIndex = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        if (maxIndex > start) {
            root.left = getMaxTree(nums, start, maxIndex - 1);
        }
        if (maxIndex < end) {
            root.right = getMaxTree(nums, maxIndex + 1, end);
        }
        return root;
    }
}
//time: O(n), space: O(n)