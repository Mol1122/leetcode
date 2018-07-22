class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(int[] candidates, int startIndex, 
                     int target, List<Integer> combination, 
                     List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<>(combination));
            return;
        }
        
        for (int i = startIndex; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (i != 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            combination.add(candidates[i]);
            dfs(candidates, i, target - candidates[i], combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}

/* 算法：组合的DFS
** 难点：当前元素可以重复使用，startIndex从i开始，还有就是如果有重复元素只取第一个那个元素
** 时间复杂度：O(2^n) */