/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> results = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return results;
        }
        
        Collections.sort(intervals, new Comparator<Interval>() {
           public int compare(Interval i1, Interval i2) {
               return i1.start - i2.start;
           } 
        });
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start > last.end) {
                results.add(last);
                last = curr;
            } else {
                last.end = Math.max(last.end, curr.end);
            }
        }
        if (last != null) {
            results.add(last);
        }
        return results;
    }
}

/* 难点：最后的last要单独判断然后加进去 */