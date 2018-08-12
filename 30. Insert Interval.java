/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */


public class Solution {
    /*
     * @param intervals: Sorted interval list.
     * @param newInterval: new interval.
     * @return: A new interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        int idx = 0;
        while (idx < intervals.size() && intervals.get(idx).start < newInterval.start) {
            idx++;
        } 
        intervals.add(idx, newInterval);
        
        Interval last = null;
        for (Interval item : intervals) {
            if (last == null || item.start > last.end) {
                result.add(item);
                last = item;
            } else {
                last.end = Math.max(last.end, item.end);
            }
        }
        
        return result;
    }
}

/* 算法：区间类问题
** 难点：这种方法是先加item进去，所以最后并不需要加last进去。带例子进去就知道了  */