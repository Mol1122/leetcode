/* Description
Given an integer array, find the top k largest numbers in it.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


The return order is non-ascending.

Example
Example1

Input: [3, 10, 1000, -99, 4, 100] and k = 3
Output: [1000, 100, 10]
Example2

Input: [8, 7, 6, 5, 4, 3, 2, 1] and k = 5
Output: [8, 7, 6, 5, 4] */

//Method 1
public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        //heap: add: O(logk) topK(klogk)
        // Queue<Integer> minheap = new PriorityQueue<>(k, new Comparator<Integer>() {
        //     public int compare(Integer a, Integer b) {
        //         return a - b;
        //     } 
        // });
        // for (int i = 0; i < nums.length; i++) {
        //     minheap.offer(nums[i]);
        //     if (minheap.size() > k) {
        //         minheap.poll();
        //     }
        // }
        // int[] results = new int[k];
        //  for (int i = 0; i < results.length; i++) {
        //      results[k - i - 1] = minheap.poll();
        //  }
        // return results;
         
        //quick sort: add:O(1), topK(nlogn)
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        quickSort(nums, 0, nums.length - 1);
        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            results[i] = nums[i];
        }
        return results;
    }
    
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start, right = end;
        int pivot = nums[(start + end) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
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
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
}

//Method 2
public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Queue<Integer> minheap = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });
        for (int num :  nums) {
            minheap.offer(num);
            if (minheap.size() > k) {
                minheap.poll();
            }
        }
        k = minheap.size();
        int[] results = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            results[i] = minheap.poll();
        }
        return results;
    }
}
//time: insert: O(nlogk), topK(): O(nlogk)