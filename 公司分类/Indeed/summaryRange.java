public static String summaryRangeDiscontinue(int[] nums) {
        StringBuilder sb = new StringBuilder();
        int start = 0, end = 0, diff = 0;

        while (end < nums.length) {
            if (end + 1 < nums.length) {
                diff = nums[end + 1] - nums[end];
            }
            while (end + 1 < nums.length && nums[end] + diff == nums[end + 1]) {
                end++;
            }
            if (end == nums.length - 1) {
                sb.append(nums[start] + "-" + nums[end] + "/" + diff);
            } else {
                sb.append(nums[start] + "-" + nums[end] + '/' + diff + ",");
            }
            end++;
            start = end;
        }
        return sb.toString();
    }

    public static List<String> summaryRangeOutOfOrder(int[] nums) {
        List<String> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<int[]> temp = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (set.contains(num)) {
                int start = num;
                int end = num;
                set.remove(num);
                while (set.contains(start - 1)) {
                    start--;
                    set.remove(start);
                }
                while (set.contains(end + 1)) {
                    end++;
                    set.remove(end);
                }
                temp.add(new int[]{start, end});
            }
        }
        Collections.sort(temp, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        for (int[] arr : temp) {
            if (arr[0] == arr[1]) {
                results.add(arr[0] + "");
            } else {
                results.add(arr[0] + "->" + arr[1]);
            }
        }
        return results;
    }

    public static List<String> summaryRangeBinarySearch(int[] nums) {
        List<String> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        int i = 0;
        while (i < nums.length) {
            int j = findRight(nums, i, nums.length - 1);
            if (j - i + 1 == 1) {
                results.add(nums[i] + "");
            } else {
                results.add(nums[i] + "->" + nums[j]);
            }
            j++;
            i = j;
        }
        return results;
    }