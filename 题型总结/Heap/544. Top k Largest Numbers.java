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


//Method 3: Quick Select
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
        quickSelect(nums, 0, nums.length - 1, nums.length - k);

        int[] results = Arrays.copyOfRange(nums, nums.length - k, nums.length);

        Arrays.sort(results);
        reverse(results);
        return results;
    }

    private void reverse(int[] results) {
        int left = 0, right = results.length - 1;

        while (left < right) {
            int temp = results[left];
            results[left] = results[right];
            results[right] = temp;
            left++;
            right--;
        }
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[start];
        }
        int left = start, right = end;
        int pivot = nums[(left + right) / 2];

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
            quickSelect(nums, start, right, k);
        }
        if (k >= left) {
            quickSelect(nums, left, end, k);
        }
        return nums[k];
    }
}
/* Time Complexity

Quickselect step

1. On average, Quickselect runs in O(n) time, because each partition reduces the problem size roughly in half.

In the worst case (bad pivot choices every time), it degrades to O(n²).

With randomized pivot selection (or median-of-three), the average O(n) is typically guaranteed in practice.

2. Copying out the last k elements

Arrays.copyOfRange(nums, n - k, n) → O(k).

3. Sorting the k elements (optional)

Arrays.sort(result) → O(k log k).

Reversing → O(k).

So:

If you return them unsorted, total = O(n + k) ≈ O(n).

If you return them sorted descending, total = O(n + k log k).


Space Complexity

In-place Quickselect partitioning → O(1) extra space.

Copy result array → O(k) space.

Recursion depth (if using recursive quickselect) → up to O(log n) average, worst O(n) (tail recursion).

So:

Total = O(k + log n) average, O(k + n) worst-case recursion. */