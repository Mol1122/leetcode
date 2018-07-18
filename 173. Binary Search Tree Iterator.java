/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode curr = stack.peek();
        TreeNode node = curr;
        
        //move to the next node, 在这之后stack peek()就是node所指的地方
        if (node.right != null) {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        } else {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
        }
        return curr.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

/* 算法： 阅读全文并背诵
这是一个非常通用的利用 stack 进行 Binary Tree Iterator 的写法。

stack 中保存一路走到当前节点的所有节点，stack.peek() 一直指向 iterator 指向的当前节点。
因此判断有没有下一个，只需要判断 stack 是否为空
获得下一个值，只需要返回 stack.peek() 的值，并将 stack 进行相应的变化，挪到下一个点。

挪到下一个点的算法如下：

如果当前点存在右子树，那么就是右子树中“一路向西”最左边的那个点
如果当前点不存在右子树，则是走到当前点的路径中，第一个左拐的点
*/