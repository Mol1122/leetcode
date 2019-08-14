/* One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. 
If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. 
Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".   

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false */

public class Solution {
  public boolean isValidSerialization(String preorder) {
    if (preorder == null || preorder.length() == 0) {
      return false;
    }
    String[] elements = preorder.split(",");
    Deque<String> stack = new ArrayDeque<>();

    for (int i = 0; i < elements.length; i++) {
      if (elements[i].equals("#")) {
        while (!stack.isEmpty() && stack.peekLast().equals("#")) {
          stack.pollLast();
          if (!stack.isEmpty()) {
            stack.pollLast();
          } else {
            return false;
          }
        }
        stack.offerLast(elements[i]);
      } else {
        stack.offerLast(elements[i]);
      }
    }
    return stack.size() == 1 && stack.pollLast().equals("#");
  }
}
//time: O(n), space: O(n)
//算法：stack可以理解成莫逆recursion, 因为我们没有办法从root到leaf判断，必须要先知道leaf的情况。
//     你可以想一下如果遍历到了leaf node, 一定是digit + # + # 这种pattern. 所以我们从后往前检查，一旦确定以这个digit为root的subtree是valid的，就可以从stack pop出去，
//     但是为了标记这个subtree已经是valid的信息给它的parent, 最后再push进去一个 # , 因为 # 也可以表示一个valid subtree的情况