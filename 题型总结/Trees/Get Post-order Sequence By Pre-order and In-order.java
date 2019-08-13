/* Given Inorder and Preorder traversals of a binary tree, get the Postorder traversal without reconstructing a binary tree.

Assumptions:

The given Inorder and Preorder traversals are guaranteed to be valid.
Examples:

Input:

Inorder traversal in[] = {4, 2, 5, 1, 3, 6}
Preorder traversal pre[] = {1, 2, 4, 5, 3, 6}

Output:
Postorder traversal is {4, 5, 2, 6, 3, 1}    */

public class Solution {
  public int[] postOrder(int[] pre, int[] in) {
    List<Integer> post = new ArrayList<>();
    int[] preLeft = new int[] {0};
    int[] inLeft = new int[] {0};
    construct(pre, preLeft, in, inLeft, Integer.MAX_VALUE, post);

    int[] results = new int[post.size()];
    for (int i = 0; i < post.size(); i++) {
      results[i] = post.get(i);
    }    
    return results;
  }

  private void construct(int[] pre, int[] preLeft, int[] in, int[] inLeft, int inorderSuccessor, List<Integer> post) {
    if (inLeft[0] == in.length || in[inLeft[0]] == inorderSuccessor) { //第二个只能是等于，因为遍历到右子树的时候是可以大于inorderSuccessor的
      return;
    }
    int rootVal = pre[preLeft[0]];
    preLeft[0]++;
    construct(pre, preLeft, in, inLeft, rootVal, post);
    inLeft[0]++;
    construct(pre, preLeft, in, inLeft, inorderSuccessor, post);
    post.add(rootVal);
  }
}
//算法： follow postorder traversal
//time: O(n), space: O(n)
//难点：preLeft, inLeft必须是global的，不然第二次construct的时候index还是第一次没有移动时候的位置