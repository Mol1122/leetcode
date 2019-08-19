/* Given N pairs of parentheses “()”, return a list with all the valid permutations.

Assumptions

N > 0
Examples

N = 1, all valid permutations are ["()"]
N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"] */

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        //dfs(n, n, "", results);
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
    
    // private void dfs(int leftParen, int rightParen, String paren, List<String> results) {
    //     if (leftParen == 0 && rightParen == 0) {
    //         results.add(paren);
    //         return;
    //     }
    //     if (leftParen > 0) {
    //         dfs(leftParen - 1, rightParen, paren + "(", results);
    //     }
    //     if (rightParen > 0 && leftParen < rightParen) {
    //         dfs(leftParen, rightParen - 1, paren + ")", results);
    //     }
    // }
}

/* 时间复杂度：O(2^(2n))
** 空间复杂度：O(2n)
                empty
              /       \
            (          )
          /    \
         ((    ) 
              
*/