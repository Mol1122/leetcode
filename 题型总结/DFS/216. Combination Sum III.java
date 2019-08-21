/* Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]] */

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        if (k <= 0 || n <= 0) {
            return results;
        }
        dfs(1, k, n, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(int index, int k, int n, List<Integer> combination, List<List<Integer>> results) {
        if (combination.size() == k) {
            if (n == 0) {
                results.add(new ArrayList<>(combination));    
            }
            return;
        }
        if (index > 9) {
            return;
        }
        combination.add(index);
        dfs(index + 1, k, n - index, combination, results);
        combination.remove(combination.size() - 1);
        
        dfs(index + 1, k, n, combination, results);
    }
}
//time: O(2^9), space: O(9)

//Not recommended
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        if (k <= 0 || n <= 0) {
            return results;
        }
        dfs(1, k, n, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(int index, int k, int n, List<Integer> combination, List<List<Integer>> results) {
        if (combination.size() == k && n == 0) {
            results.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i <= 9; i++) {
            combination.add(i);
            dfs(i + 1, k, n - i, combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}
//time: O(9^k), space: O(k)