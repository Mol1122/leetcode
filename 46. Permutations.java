class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }   
        dfs(nums, new boolean[nums.length], new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(int[] nums, boolean[] visited, List<Integer> permutaion, List<List<Integer>> results) {
        if (nums.length == permutaion.size()) {
            results.add(new ArrayList<>(permutaion));
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            permutaion.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutaion, results);
            //回溯
            permutaion.remove(permutaion.size() - 1);
            visited[i] = false;
        }
    }
}

/* 算法：全排列问题 permutation */