/* Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A.

Assumptions

A is not null
K is guranteed to be >= 0 and K is guranteed to be <= A.length
Return

A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T. 
Examples

A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6} */

class Solution {
    public List<Integer> findClosestElements(int[] nums, int k, int target) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        int left = getLeftClosest(nums, target);
        int right = left + 1;
        
        for (int i = 0; i < k; i++) {
            if (isLeftCloser(nums, left, right, target)) {
                results.add(nums[left--]);
            } else {
                results.add(nums[right++]);
            }
        }
        Collections.sort(results);
        return results;
    }
    
    private boolean isLeftCloser(int[] nums, int left, int right, int target) {
        if (left < 0) {
            return false;
        }
        if (right >= nums.length) {
            return true;
        }
        return Math.abs(target - nums[left]) <= Math.abs(target - nums[right]);
    }
    
    private int getLeftClosest(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] <= target) {
            return end;
        }
        if (nums[start] <= target) {
            return start;
        } else {
            return -1;
        }
    }
}


/* 中心开花算法：找到最后一个数< target,然后再用两个指针分别向两边走去找k个数值最近的值
** 时间复杂度：O(logn + k) 
** 转换成两个sorted array找第k小的题 -> [1, 2, 3,|| 8, 9], target = 4, k = 3
**                       leftArray={3, 2, 1}         rightArray={4, 5}
**                                 <--L                R-->
** 时间复杂度：O(logn + logk) = O(logn): logn ->找到分界点， logk ->找到第k小的数(因为是A里找第k/2个数，B里找k/2个数，然后每次去掉小的那k/2个数，每次去掉k的一半，所以是logk)
*/

