class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        
        if (n % 2 == 0) {
            return (findKth(nums1, nums2, n / 2) + findKth(nums1, nums2, n / 2 + 1)) / 2.0;
        }
        return (findKth(nums1, nums2, n / 2 + 1));
    }
    
    private int findKth(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0) {
            return nums2[k - 1];
        }
        if (nums2.length == 0) {
            return nums1[k - 1];
        }
        int start = Math.min(nums1[0], nums2[0]);
        int end = Math.max(nums1[nums1.length - 1], nums2[nums2.length - 1]);
        //find the value of the first Kth element (k个小于或等于它的值)
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (countSmallerOrEqual(nums1, mid) + countSmallerOrEqual(nums2, mid) < k) { //难点：< k
                start = mid;
            } else {
                end = mid;
            }
        }
        if (countSmallerOrEqual(nums1, start) + countSmallerOrEqual(nums2, start) >= k) {
            return start;
        }
        return end;
    }
    
    private int countSmallerOrEqual(int[] arr, int number) {
        int start = 0, end = arr.length - 1;
        
        //find the first index that is > number
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= number) { //难点：<=
                start = mid;
            } else {
                end = mid;
            }
        }
        if (arr[start] > number) {
            return start;
        }
        if (arr[end] > number) {
            return end;
        }
        return arr.length;
    }
}

/* 算法：二分值域猜测可能的第k个值，然后再二分去数多少个数小于那个猜测的第k个值
** 难点：两次二分都希望找到第一个比k/number大的数
** 时间复杂度：O(range *(O(nums1.length) + O(nums2.length))) */