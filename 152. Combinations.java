public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        if (k > n) {
            return results;
        }
        dfs(n, 1, k, new ArrayList<Integer>(), results);
        return results;
    }
    
    private void dfs(int n, int startIndex, int limit, List<Integer> combination, 
                     List<List<Integer>> results) {
        if (limit == 0) {
            results.add(new ArrayList<>(combination));
            return;
        }
        
        for (int i = startIndex; i <= n; i++) {
            if (limit <= 0) {
                break;
            }
            combination.add(i);
            dfs(n, i + 1, limit - 1, combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}