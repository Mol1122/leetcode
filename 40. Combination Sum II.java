class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        Arrays.sort(candidates);
        List<Integer> combination = new ArrayList<>();
        dfs(candidates, 0, target, combination, results);
        return results;
    }
    
    
    private void dfs(int[] candidates, int startIndex, int target, 
                     List<Integer> combination, List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<>(combination));
            return;
        }
        
        for (int i = startIndex; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (i != startIndex && candidates[i] == candidates[i - 1]) { //去重
                continue;
            }
            combination.add(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}

/* 算法：组合类的DFS
** 难点：如何去重->选代表
** 时间复杂度: O(2^n) */