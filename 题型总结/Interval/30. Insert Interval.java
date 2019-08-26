/* Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Example
Example 1:

Input:
(2, 5) into [(1,2), (5,9)]
Output:
[(1,9)]
Example 2:

Input:
(3, 4) into [(1,2), (5,9)]
Output:
[(1,2), (3,4), (5,9)] */

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: Sorted interval list.
     * @param newInterval: new interval.
     * @return: A new interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> results = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            results.add(newInterval);
            return results;
        }
        int idx = 0;
        while (idx < intervals.size() && intervals.get(idx).start < newInterval.start) {
            idx++;
        }
        intervals.add(idx, newInterval);
        
        Interval last = intervals.get(0);
        for (Interval curr : intervals) {
            if (curr.start > last.end) {
                results.add(last);
                last = curr;
            } else {
                last.end = Math.max(last.end, curr.end);
            }
        }
        results.add(last);
        return results;
    }
}
//算法：先找到要插入的位置插入，最后再merge
//time: O(n), space: O(1)