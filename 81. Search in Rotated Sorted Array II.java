class Solution {
    public boolean search(int[] nums, int target) {
        // for (int i = 0; i < nums.length; i ++) {
        //     if (nums[i] == target) {
        //         return true;
        //     }
        // }
        // return false;
        
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[start] < nums[mid]) { //left side is sorted
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[start] > nums[mid]) { //right side is sorted
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else { //难点：如果start和mid相等，那么让start++直到这两个数不一样为止，这样就能解决start和mid一样，target可能在左边也可能在右边的情况
                start++;;
            }
        }
        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        return false;
    }
}

// 这个问题在面试中不会让实现完整程序
// 只需要举出能够最坏情况的数据是 [1,1,1,1... 1] 里有一个0即可。
// 在这种情况下是无法使用二分法的，复杂度是O(n)
// 因此写个for循环最坏也是O(n)，那就写个for循环就好了
//  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
//  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。