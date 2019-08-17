/* Given an integer array A and a sliding window of size K, find the maximum value of each window as it slides from left to right.

Assumptions

The given array is not null and is not empty

K >= 1, K <= A.length

Examples

A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},

and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4] */


//Deque: time: O(n), space: O(n)
public class Solution {
  public List<Integer> maxWindows(int[] nums, int k) {
    List<Integer> results = new ArrayList<>();
    if (nums == null || nums.length == 0) {
        return results;
    }
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < nums.length; i++) {
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) { //从最近期的开始检查，如果不如当前值大，就删掉.不然会影响下一轮的值
            deque.pollLast();
        }
        if (!deque.isEmpty() && deque.peekFirst() <= i - k) { //<= 容易错
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


//TreeSet: time: O(nlogk), space: O(n)
public class Solution {
  public List<Integer> maxWindows(int[] nums, int k) {
    List<Integer> results = new ArrayList<>();
    if (nums == null || nums.length == 0) {
        return results;
    }
    TreeSet<Pair> set = new TreeSet<>(new Comparator<Pair>(){
        public int compare(Pair a, Pair b) {
            if (a.val == b.val) {
                return a.index - b.index;
            }
            return b.val - a.val;
        }
    });
    for (int i = 0; i < nums.length; i++) {
        Pair p = new Pair(nums[i], i);
        set.add(p);
        if (set.size() > k) {
            set.remove(new Pair(nums[i - k], i - k));
        }
        if (set.size() == k) {
            results.add(set.first().val);
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

