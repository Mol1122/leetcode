/* Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature. */

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        
        List<int[]> list = new ArrayList<>();
        int[] last = intervals[0];
        
        for (int[] curr : intervals) {
            if (curr[0] > last[1]) {
                list.add(last);
                last = curr;
            } else {
                last[1] = Math.max(last[1], curr[1]);
            }
        }
        if (last != null) {
            list.add(last);
        }
        int[][] results = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
        }
        return results;
    }
}
//time: O(nlogn), space: O(n)