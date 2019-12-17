/* Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
] */

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        dfs(n, 0, 0, new StringBuilder(), results);
        return results;
    }
    
    private void dfs(int n, int leftCount, int rightCount, 
                   StringBuilder paren, List<String> results) {
      if (leftCount == n && rightCount == n) {
          results.add(new String(paren));
          return;
      }
      if (leftCount < n) {
          paren.append("(");
          dfs(n, leftCount + 1, rightCount, paren, results);
          paren.deleteCharAt(paren.length() - 1);
      }
      if (leftCount > rightCount) {
          paren.append(")");
          dfs(n, leftCount, rightCount + 1, paren, results);
          paren.deleteCharAt(paren.length() - 1);
      }
  }
}

/* 时间复杂度：O(2^(2n))
** 空间复杂度：O(2n)
                empty
              /       \
            (          )
          /    \
         ((    ) 
              
*/