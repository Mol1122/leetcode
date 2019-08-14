/* Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree: 

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false
Example 2:

Input: [5,2,1,3,6]
Output: true */

class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int lowbound = Integer.MIN_VALUE;
        
        for (int num : preorder) {
            if (num < lowbound) {
                return false;
            }
            while (!stack.isEmpty() && num > stack.peekLast()) {
                lowbound = stack.pollLast(); //去到右子树的时候，value一定要大于left subtree value
            }
            stack.offerLast(num);
        }
        return true;
    }
}
//time: O(n), space: O(n)