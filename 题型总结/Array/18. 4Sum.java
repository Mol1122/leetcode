class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> results = new HashSet<>();
        if (nums == null || nums.length < 4) {
            return new ArrayList<>(results);
        }
        Map<Integer, List<int[]>> sum2index = new HashMap<>();
        for (int j = 1; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                int sum = nums[i] + nums[j];
                if (sum2index.containsKey(target - sum)) {
                    for (int[] pair : sum2index.get(target - sum)) {
                        if (pair[1] >= i) {
                            continue;
                        }
                        List<Integer> temp = Arrays.asList(nums[pair[0]], nums[pair[1]], nums[i], nums[j]);
                        Collections.sort(temp);
                        results.add(temp);
                    }
                }
                sum2index.putIfAbsent(sum, new ArrayList<>());
                sum2index.get(sum).add(new int[]{i, j});
            }
        }
        return new ArrayList<>(results);
    }
}
//time: O(n^2), space: O(n)