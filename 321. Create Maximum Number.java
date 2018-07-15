class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] results = new int[k];
        if (nums1 == null || nums2 == null) {
            return null;
        }
        int m = nums1.length;
        int n = nums2.length;
        if (k == 0 || m + n < k) {
            return results;
        }
        if (m + n == k) {
            results = merge(nums1, nums2, k);
            return results;
        } else {
            int max = Math.min(k, m); //nums1里最多取几个数
            int min = Math.max(0, k - n); //nums1里最少取几个数
            
            for (int i = min; i <= max; i++) {
                int[] temp = merge(getMax(nums1, i), getMax(nums2, k - i), k);
                results = isGreater(results, 0, temp, 0) ? results : temp;
            }
        }
        return results;
    }
    
    private int[] getMax(int[] nums, int k) {
        int[] results = new int[k];
        int i = 0; //keep track the index in results
        for (int j = 0; j < nums.length; j++) {
            //nums还剩多少个数没遍历 > results还有多少位置待填满
            while (nums.length - j > k - i && i > 0 && results[i - 1] < nums[j]) {
                i--; //回过头来要把ith的数替换掉
            }
            if (i < k) {
                results[i++] = nums[j];
            }
        }
        return results;
    }
    
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] results = new int[k];
        int i = 0, j = 0;
        for (int l = 0; l < k; l++) {
            results[l] = isGreater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return results;
    }
    
    private boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
        for (; i < nums1.length && j < nums2.length; i++, j++) {
            if (nums1[i] > nums2[j]) {
                return true;
            }
            if (nums1[i] < nums2[j]) {
                return false;
            }
        }
        return i != nums1.length; //如果nums1比较短，那么要保证nums2里的数能被取到
    } 
}

/* 算法：坐标型动态规划，enumerate nums1取多少个数
** 时间复杂度：O((n + m)k) */