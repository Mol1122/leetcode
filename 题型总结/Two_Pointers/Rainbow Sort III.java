/* Given an array of balls with k different colors denoted by numbers 1- k, sort the balls.

Examples

k=1, {1} is sorted to {1}
k=3, {1, 3, 2, 1, 2} is sorted to {1, 1, 2, 2, 3}
k=5, {3, 1, 5, 5, 1, 4, 2} is sorted to {1, 1, 2, 3, 4, 5, 5}
Assumptions

The input array is not null.
k is guaranteed to be >= 1.
k << logn.
 */


//Method 1
public class Solution {
  public int[] rainbowSortIII(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    return countingSort(nums, 1, k);
  }

  private int[] countingSort(int[] nums, int lower, int upper) {
    int[] count = new int[upper - lower + 1];
    for (int num : nums) {
      count[num - lower]++;
    }
    int index = 0;
    for (int i = 0; i < count.length; i++) {
      while (count[i] > 0) {
        nums[index++] = i + lower;
        count[i]--;
      }
    }
    return nums;
  }
}
//time: O(n), space: O(1)


//Method 2
public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }

    private void rainbowSort(int[] nums, int start, int end, int colorStart, int colorEnd) {
        if (colorStart == colorEnd) {
            return;
        }
        if (start >= end) {
            return;
        }
        int left = start, right = end;
        int colorMid = (colorStart + colorEnd) / 2;

        while (left <= right) {
            while (left <= right && nums[left] <= colorMid) {
                left++;
            }
            while (left <= right && nums[right] > colorMid) {
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
        rainbowSort(nums, start, right, colorStart, colorMid);
        rainbowSort(nums, left, end, colorMid + 1, colorEnd);
    }
}