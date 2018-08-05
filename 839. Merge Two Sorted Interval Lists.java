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
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        List<Interval> results = new ArrayList<>();
        if (list1 == null || list2 == null) {
            return results;
        }
        int i = 0, j = 0;
        Interval last = null, curr = null;
        
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).start <= list2.get(j).start) {
                curr = list1.get(i++);
                last = merge(results, last, curr);
            } else {
                curr = list2.get(j++);
                last = merge(results, last, curr);
            }
        }
        while (i < list1.size()) {
            curr = list1.get(i++);
            last = merge(results, last, curr);
        }
        while (j < list2.size()) {
            curr = list2.get(j++);
            last = merge(results, last, curr);
        }
        if (last != null) {
            results.add(last);
        }
        return results;
    }
    
    private Interval merge(List<Interval> results, Interval last, Interval curr) {
        if (last == null) {
            return curr;
        }
        if (curr.start > last.end) {
            results.add(last);
            return curr;
        }
        last.end = Math.max(last.end, curr.end);
        return last;
    }
}