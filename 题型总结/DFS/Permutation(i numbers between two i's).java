/* Given two copies of all integers from 1 to n: input[2N] = {1, 1, 2, 2, ..., n, n}. 
Please determine whether it is possible to shuffle the numbers, such that for each i, there are 
exactly i numbers between the two i's */

public class Solution {
	public boolean allPermutation(int[] nums, int n) {
		if (nums == null || nums.length == 0 || n <= 1) {
			return false;
		}
		List<List<Integer>> results = new ArrayList<>();
		Map<Integer, Integer> num2count = new HashMap<>();
		dfs(nums, n, 1, num2count, new ArrayList<>(), results);
		return results.size() != 0;
	}

	private void dfs(int[] nums, int n, int index, Map<Integer, Integer> num2count, List<Integer> permutation, List<List<Integer>> results) {
		if (index > 2 * n) {
			if (permutation.size() == nums.length) {
				results.add(new ArrayList<>(permutation));
			}
			return;
		}
		for (int i = index; i <= 2 * n; i++) {
			if (!num2count.containsKey(nums[i]) || 
				(num2count.get(nums[i]) == 1 && (i - 8) >= 0 && permutation.get(i - 8) == nums[i])) {
				num2count.putIfAbsent(nums[i], 0);
				num2count.put(nums[i], num2count.get(nums[i]) + 1);
				swap(nums, index, i);
				dfs(nums, n, index + 1, num2count, permutation, results);
				swap(nums, index, i);
				num2count.put(nums[i], num2count.get(nums[i]) - 1);
				if (num2count.get(nums[i]) == 0) {
					num2count.remove(nums[i]);
				}
			}
		}
	}
}
//time: O(n!), space: O(2n)