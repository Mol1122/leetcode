public static int subsetNum2(int[] nums, int target) { //time:O(n^2)
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < nums.length && nums[left] <= target / 2) {
            while (left < right && nums[left] + nums[right] > target) {
                right--;
            }
            count += Math.pow(2,(right - left)); //1,2,3. 1must in subset, 2,3optional
            left ++;
            right = nums.length - 1;
        }
        return count;
    }

public static int subsetNum(int[] nums, int target) { //not quite right
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        dfs(nums, 0, target, new ArrayList<>(), results);
        for (List<Integer> list : results) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
        return results.size() - 1; //since Empty set could also be considered as subset
    }

    private static void dfs(int[] nums, int startIndex, int target, List<Integer> subset,
                            List<List<Integer>> results) {
        results.add(new ArrayList<>(subset));

        for (int i = startIndex; i < nums.length; i++) {
            if (subset.size() > 0 && subset.get(0) + nums[i] > target) {
                continue;
            }
            if (nums[i] > target) {
                continue;
            }
            subset.add(nums[i]);
            dfs(nums, i + 1, target, subset, results);
            subset.remove(subset.size() - 1);
        }
    }