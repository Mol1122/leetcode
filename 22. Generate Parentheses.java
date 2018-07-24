class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        dfs(n, n, "", results);
        return results;
    }
    
    private void dfs(int left, int right, String paren, List<String> results) {
        if (left == 0 && right == 0) {
            results.add(paren);
            return;
        }
        if (left > 0) {
            dfs(left - 1, right, paren + "(", results);
        }
        if (right > 0 && left < right) {
            dfs(left, right - 1, paren + ")", results);
        }
    }
}

/* 算法：搜索类DFS
        当用通用的for loop去做搜索很难想的时候，要想想简单版的加或者不加，不能被自己的思维定式限制死
   难点：不要用StringBuilder, 直接用string就可以 */