class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<Integer> subsets = new ArrayList<>();
        subsetsDFS(nums, 0, subsets, results);
        return results;
    }
    
    private void subsetsDFS(int[] nums, int index, List<Integer> subsets, List<List<Integer>> results) {
        if (index == nums.length) {
            results.add(new ArrayList<>(subsets));
            return;
        }
        subsets.add(nums[index]);
        subsetsDFS(nums, index + 1, subsets, results);
        subsets.remove(subsets.size() - 1);
        subsetsDFS(nums, index + 1, subsets, results);
    }
}

/* 算法：使用组合类搜索的专用深度优先搜索算法。
一层一层的决策每个数要不要放到最后的集合里。  

** 时间复杂度：O(2^n) */