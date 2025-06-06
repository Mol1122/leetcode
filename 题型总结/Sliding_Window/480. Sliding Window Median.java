/* The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle values.

For examples, if arr = [2,3,4], the median is 3.
For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very 
left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves 
right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation: 
Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7        1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7        3
 1  3  -1  -3 [5  3  6] 7        5
 1  3  -1  -3  5 [3  6  7]       6
Example 2:

Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000] */

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new double[0];
        }
        Queue<Integer> minheap = new PriorityQueue<>();
        Queue<Integer> maxheap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        });
        List<Double> list = new ArrayList<>();

        for (int i = 0; i <= nums.length; i++) {
            if (i >= k) {
                list.add(getMedian(maxheap, minheap));
                remove(maxheap, minheap, nums[i - k]);
            }
            if (i < nums.length) {
                add(maxheap, minheap, nums[i]);   
            }
        }
        double[] results = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
        } 
        return results;
    }
    
    private void add(Queue<Integer> maxheap, Queue<Integer> minheap, int num) {
        if (num <= getMedian(maxheap, minheap)) {
            maxheap.offer(num);
        } else {
            minheap.offer(num);
        }
        if (minheap.size() > maxheap.size()) {
            maxheap.offer(minheap.poll());
        }
        if (maxheap.size() - minheap.size() > 1) {
            minheap.offer(maxheap.poll());
        }
    }
    
    private void remove(Queue<Integer> maxheap, Queue<Integer> minheap, int num) {
        if (num <= getMedian(maxheap, minheap)) {
            maxheap.remove(num);
        } else {
            minheap.remove(num);
        }
        if (minheap.size() > maxheap.size()) {
            maxheap.offer(minheap.poll());
        }
        if (maxheap.size() - minheap.size() > 1) {
            minheap.offer(maxheap.poll());
        }
    }
    
    private double getMedian(Queue<Integer> maxheap, Queue<Integer> minheap) {
        if (maxheap.isEmpty() && minheap.isEmpty()) {
            return 0;
        }

        if (maxheap.size() == minheap.size()) {
            return ((double)maxheap.peek() + (double)minheap.peek()) / 2.0;
        }
        else {
            return (double)maxheap.peek();
        }
    }
}
//这题跟find median in data steram的区别是，这题不断在删除