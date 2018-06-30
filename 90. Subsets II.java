class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        Arrays.sort(nums);
        List<Integer> subset = new ArrayList<>();
        subsetsDFS(nums, 0, subset, results);
        return results;
        
        
    }
    public void subsetsDFS(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> results){
        //deep copy and add to the result
        results.add(new ArrayList<Integer>(subset));
        
        for(int i = startIndex; i < nums.length; i++){
            // i != 0是为了不越界，因为i - 1可能会越界
            // nums[i] == nums[i - 1]是为了判断，当前数是否跟前一个数相同
            // i > startIndex是判断有没有跳数
            // startIndex - 1, >= startIndex + 1, 说明中间至少跳了一个数,如果和前面的数相同，往下走肯定会有重复(因为你走的这个2''， 2'也走过)
            if (i != 0 && nums[i] == nums[i - 1] && i > startIndex) {
                continue;
            }
            subset.add(nums[i]);
            subsetsDFS(nums, i + 1, subset, results);
            subset.remove(subset.size() - 1);
        }
    }
}

/* 算法：搜索类dfs如何去重, 关键点就在于[1, 2', 2''] 不能跳过2'直接取到2''. 方法就是判断当前数是否跟之前的数相同，并且有没有跳过一个数
** 时间复杂的：O(2^n) */