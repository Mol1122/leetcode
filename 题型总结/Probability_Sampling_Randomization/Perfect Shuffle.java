public class Solution {
  public void shuffle(int[] nums) {
      if (nums == null || nums.length == 0) {
          return;
      }
      int n = nums.length;
      Random rand = new Random();
    
      for (int index = 0; index < nums.length; index++) {
          int j = index + rand.nextInt(n - index);
          swap(nums, index, j);
      }
  }
  
  private void swap(int[] nums, int i, int j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
}
//time: O(n), space: O(1)
