/* Given an array nums, there is a sliding window of size k which is moving from the very 
left of the array to the very right. You can only see the k numbers in the window. Each 
time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note:
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time? */
//time: O(n), space: O(n)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new int[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                stack.pollLast();
            }
            if (!stack.isEmpty() && stack.peekFirst() <= i - k) {
                stack.pollFirst();
            }
            stack.offerLast(i);
            if (i >= k - 1) {
                list.add(nums[stack.peekFirst()]);
            }
        }
        int[] results = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
        }
        return results;
    }
}

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new int[0];
        }
        TreeSet<Pair> maxheap = new TreeSet<>(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                if (a.val == b.val) {
                    return a.index - b.index;
                }
                return b.val - a.val;
            }
        });
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            maxheap.add(new Pair(nums[i], i));
            if (maxheap.size() > k) {
                Pair delete = new Pair(nums[i - k], i - k);
                maxheap.remove(delete);
            }
            if (maxheap.size() == k) {
                list.add(maxheap.first().val);
            }
        }
        int[] results = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
        }
        return results;
    }
}

class Pair {
    int val, index;
    public Pair(int val, int index) {
        this.val = val;
        this.index = index;
    }
}