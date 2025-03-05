/* You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.

The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note that i may equal j.

Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.

 

Example 1:

Input: intervals = [[1,2]]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:

Input: intervals = [[3,4],[2,3],[1,2]]
Output: [-1,0,1]
Explanation: There is no right interval for [3,4].
The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
Example 3:

Input: intervals = [[1,4],[2,3],[3,4]]
Output: [-1,2,-1]
Explanation: There is no right interval for [1,4] and [3,4].
The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3. */

class Solution {
    public int[] findRightInterval(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0];
        }
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            list.add(new Pair(intervals[i][0], i));
        }
        Collections.sort(list, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.val - b.val;
            }
        });

        int[] results = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int target = intervals[i][1];
            int start = 0, end = list.size() - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (list.get(mid).val >= target) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
            if (list.get(start).val >= target) {
                results[i] = list.get(start).index;
            } else if (list.get(end).val >= target) {
                results[i] = list.get(end).index;
            } else {
                results[i] = -1;
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
//time: O(nlogn). space: O(n)