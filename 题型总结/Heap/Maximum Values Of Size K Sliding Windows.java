/* Given an integer array A and a sliding window of size K, find the maximum value of each window as it slides from left to right.

Assumptions

The given array is not null and is not empty

K >= 1, K <= A.length

Examples

A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},

and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4] */

// Using TreeSet
public class Solution {
  public List<Integer> maxWindows(int[] nums, int k) {
    TreeSet<Pair> maxheap = new TreeSet<>(new Comparator<Pair>(){
        public int compare(Pair a, Pair b) {
            if (a.val == b.val) {
                return a.index - b.index;
            }
            return b.val - a.val;
        }
    });
    List<Integer> results = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
        Pair p = new Pair(nums[i], i);
        maxheap.add(p);

        if (maxheap.size() > k) {
            Pair delete = new Pair(nums[i - k], i - k);
            maxheap.remove(delete);
        }
        if (maxheap.size() == k) {
            results.add(maxheap.first().val);
        }
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
//time: O(nlogk), space: O(n)

// Using Deque, 单调递减栈
public class Solution {
  public List<Integer> maxWindows(int[] nums, int k) {
    List<Integer> results = new ArrayList<>();
    if (nums == null || nums.length < k) {
        return results;
    }
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < nums.length; i++) {
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }
        if (!deque.isEmpty() && deque.peekFirst() <= i - k) { //只可能最前面一个out of bound
            deque.pollFirst();
        }
        deque.offerLast(i);
        if (i >= k - 1) {
            results.add(nums[deque.peekFirst()]);
        }
    }
    return results;
  }
}
//time: O(n), space: O(n)
