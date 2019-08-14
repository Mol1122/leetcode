/* Implement a solution to parse a ternary expression into a tree.

Assumptions:

The input ternary expression is a string, and it is guaranteed to be valid.
Examples:

a?b:c  -->

   a

 /   \

b     c

a?b?c:d:e  -->

      a

    /    \

  b       e

/    \

c    d     */

public class Solution {
  public ExpNode tree(String exp) {
    if (exp == null || exp.length() == 0) {
      return null;
    }
    char[] expression = exp.toCharArray();
    int[] index = {0};
    return getNode(expression, index);
  }

  private ExpNode getNode(char[] expression, int[] index) {
    if (index[0] >= expression.length) {
      return null;
    }
    ExpNode root = new ExpNode(expression[index[0]]);
    index[0]++;
    boolean hasRight = false;
    if (index[0] < expression.length && expression[index[0]] == '?') {
      index[0]++;
      root.left = getNode(expression, index);
      hasRight = true;
    }
    if (index[0] < expression.length && expression[index[0]] == ':' && hasRight) {
      index[0]++;
      root.right = getNode(expression, index);
    }
    return root;
  }
}
//time: O(n), space: O(n)
/* 不要定义成class field的就不要定义成field

laicode后台只会create一个Solution object，然后多次call tree()这个method在不同的test case上，如果你把index定义成field，
之前test case run过了之后的结果会保存下来对之后的结果有影响 */