/* The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above. */

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        dfs(n, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(int n, List<Integer> temp, List<List<String>> results) {
        if (temp.size() == n) {
            results.add(drawboard(new ArrayList<>(temp)));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(temp, i)) {
                temp.add(i);
                dfs(n, temp, results);
                temp.remove(temp.size() - 1);
            }
        }
    }
    
    private List<String> drawboard(List<Integer> temp) {
        List<String> results = new ArrayList<>();
        
        for (int i = 0; i < temp.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < temp.size(); j++) {
                sb.append(temp.get(i) == j ? "Q" : ".");
            }
            results.add(sb.toString());
        }
        return results;
    }
    
    private boolean isValid(List<Integer> temp, int col) {
        int row = temp.size();
        for (int i = 0; i < row; i++) {
            if (temp.get(i) == col) {
                return false;
            }
            if (i - temp.get(i) == row - col) {
                return false;
            }
            if (i + temp.get(i) == row + col) {
                return false;
            }
        }
        return true;
    }
}
//time: O(n^n * n), space: O(n)