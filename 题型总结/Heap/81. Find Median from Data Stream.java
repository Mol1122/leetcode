/* Numbers keep coming, return the median of numbers at every time a new number added.

Example
Example 1

Input: [1,2,3,4,5]
Output: [1,1,2,2,3]
Explanation:
The medium of [1] and [1,2] is 1.
The medium of [1,2,3] and [1,2,3,4] is 2.
The medium of [1,2,3,4,5] is 3.
Example 2

Input: [4,5,1,3,2,6,0]
Output: [4,4,4,3,3,3,3]
Explanation:
The medium of [4], [4,5], [4,5,1] is 4.
The medium of [4,5,1,3], [4,5,1,3,2], [4,5,1,3,2,6] and [4,5,1,3,2,6,0] is 3.
Challenge
Total run time in O(nlogn). */

public class Solution {
    Queue<Integer> maxheap = new PriorityQueue<>(new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    });
    Queue<Integer> minheap = new PriorityQueue<>();
    
    public int[] medianII(int[] nums) {
        int[] results = new int[nums.length];
        int index = 0;
        
        for (int num : nums) {
            if (maxheap.size() < minheap.size() + 1) {
                if (minheap.isEmpty()) {
                    maxheap.offer(num);
                } else {
                    maxheap.offer(num);
                    if (maxheap.peek() > minheap.peek()) {
                        Integer max = maxheap.poll();
                        Integer min = minheap.poll();
                        maxheap.offer(min);
                        minheap.offer(max);
                    } 
                }
            } else {
                minheap.offer(num);
                if (maxheap.peek() > minheap.peek()) {
                    Integer max = maxheap.poll();
                    Integer min = minheap.poll();
                    maxheap.offer(min);
                    minheap.offer(max);
                }
            }
            results[index++] = getMedian();
        }
        return results;
    }
    
    private int getMedian() {
        return maxheap.peek();
    }
}

/* 算法：左半部分是maxheap, 右半部分是minheap */
//time: O(nlogn), space: O(n)