/**
Description: Given a number n, generate all possible BST from 1…n.

Input:   3

Output:

    1                 3       2              3      1    

      \              /         /   \          /           \    

        3        2       1        3    1               2

     /         /                              \                \

   2        1                                  2               3       */
 
public class Solution {
  public List<TreeNode> generateBSTs(int n) {
    return construct(1, n);
  }

  private List<TreeNode> construct(int start, int end) {
    List<TreeNode> results = new ArrayList<>();
    if (start > end) {
      results.add(null); //易漏，如果不加，后面的for loop根本进不去
      return results;
    }
    for (int i = start; i <= end; i++) {
      List<TreeNode> left = construct(start, i - 1);
      List<TreeNode> right = construct(i + 1, end);

      for (int j = 0; j < left.size(); j++) {
        TreeNode leftNode = left.get(j);
        for (int k = 0; k < right.size(); k++) {
          TreeNode rightNode = right.get(k);

          TreeNode node = new TreeNode(i);
          node.left = leftNode;
          node.right = rightNode;
          results.add(node);
        }
      }
    }
    return results;
  }
}
//time: O(n*2^n), space: O(n^2)