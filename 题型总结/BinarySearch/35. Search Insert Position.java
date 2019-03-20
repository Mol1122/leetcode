/* Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.

If there are multiple elements with value same as target, we should insert the target before the first existing element. */
public class Solution {
  public int searchInsert(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (target < nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] >= target) {
            return end;
        }
        return nums.length;
  }
}
