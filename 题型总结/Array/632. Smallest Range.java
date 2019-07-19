/* You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24]. */

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] result = new int[2];
        if (nums == null || nums.size() == 0) {
            return result;
        }
        Queue<int[]> minheap = new PriorityQueue<>(nums.size(), new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return nums.get(a[0]).get(a[1]) - nums.get(b[0]).get(b[1]);
            }
        });
        int max = Integer.MIN_VALUE; //所有pointers指的数里面最大的那个数，最小的数从minheap里来
        int start = 0, end = Integer.MAX_VALUE; //range的左右端点
        
        for (int i = 0; i < nums.size(); i++) {
            minheap.offer(new int[]{i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }
        while (minheap.size() == nums.size()) { //当其中一个pointer走到头的时候就可以退出了，因为剩下的pointers往下走数字会越来越大
            int[] index = minheap.poll();
            if (max - nums.get(index[0]).get(index[1]) < end - start) {
                start = nums.get(index[0]).get(index[1]);
                end = max;
            }
            if (index[1] + 1 < nums.get(index[0]).size()) {
                index[1]++;
                minheap.offer(index);
                max = Math.max(max, nums.get(index[0]).get(index[1]));
            }
        }
        return new int[]{start, end};
    }
}

/* 算法：思想来自于two pointers, 但是这里会用到多个pointers. 因为是求差值最小，所以是所有pointers都是向右移动，并且每次只移动最小值。
        pointers指的数中找到min, max, max-min就是potential range, 因为要尽可能缩小range, 所以就移动最小值。 既然是在k个list里找最大最小值，就想到了heap

   time: O(nlongk), k is # lists, n is # nodes in a list, space: O(k)
        */