/* Find the kth largest element in an unsorted array. Note that it is the kth largest element 
in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4 */

class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        return findKth(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int findKth(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }
        int left = start, right = end;
        int pivot = nums[(start + end) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        if (k <= right) {
            return findKth(nums, start, right, k);
        } else if (k >= left) {
            return findKth(nums, left, end, k);
        } else {
            return nums[k];
        }
    }
}
//time: O(n), space: O(n)