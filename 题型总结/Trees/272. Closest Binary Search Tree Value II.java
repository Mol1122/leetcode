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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> results = new ArrayList<>();
        if (root == null || k <= 0) {
            return results;
        }
        Stack<TreeNode> lowerStack = getStack(root, target);
        Stack<TreeNode> upperStack = new Stack<>();
        upperStack.addAll(lowerStack);
        
        if (target < lowerStack.peek().val) {
            moveLower(lowerStack);
        } else {
            moveUpper(upperStack);
        }
        
        for (int i = 0; i < k; i++) {
            if (lowerStack.isEmpty() ||
                   !upperStack.isEmpty() && 
                    target - lowerStack.peek().val > upperStack.peek().val - target) {
                results.add(upperStack.peek().val); //不可以直接pop(), 因为move的过程可能会push新的node进去
                moveUpper(upperStack);
            } else {
                results.add(lowerStack.peek().val);
                moveLower(lowerStack);
            }
        }
        return results;
    }
    
    //就是inorder traverse的getNext()
    private void moveUpper(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        
        if (node.right == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
            return;
        }
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    private void moveLower(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        
        if (node.left == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().left == node) {
                node = stack.pop();
            }
            return;
        }
        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }
    
    //假装要insert target到树里, 用来记录走过的节点都是那些，为了之后的iteration用
    private Stack<TreeNode> getStack(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return stack;
    }
}

/* 算法： 有了这些函数之后，就可以把整个树当作一个数组一样来处理，只不过每次 i++ 的时候要用 moveUpper，i--的时候要用 moveLower
** 时间复杂度：O(k + logn)，因为题目assume balanced. 如果没有这个assumption是O(k+h). 
** 空间复杂度 O(logn) */