/* Given a binary tree, return all root-to-leaf paths from left to right.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"] */

public class Solution {
  public String[] binaryTreePaths(TreeNode root) {
    List<String> list = getPath(root);
    String[] results = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      results[i] = list.get(i);
    }
    return results;
  }

  private List<String> getPath(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<String> list = new ArrayList<>();
    for (String path : getPath(root.left)) {
      list.add(root.key + "->" + path);
    }
    for (String path : getPath(root.right)) {
      list.add(root.key + "->" + path);
    }
    if (list.size() == 0) {
      list.add(root.key + "");
    }
    return list;
  }
}
//time: O(n^2), space: O(n^2)