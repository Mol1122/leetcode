/**
Merge two sorted (ascending) lists of interval and return it as a new sorted list. The new sorted list should be made by splicing together the intervals of the two lists and sorted in ascending order.

Example
Example1

Input: list1 = [(1,2),(3,4)] and list2 = [(2,3),(5,6)]
Output: [(1,4),(5,6)]
Explanation:
(1,2),(2,3),(3,4) --> (1,4)
(5,6) --> (5,6)
Example2

Input: list1 = [(1,2),(3,4)] and list2 = [(4,5),(6,7)]
Output: [(1,2),(3,5),(6,7)]
Explanation:
(1,2) --> (1,2)
(3,4),(4,5) --> (3,5)
(6,7) --> (6,7)
Notice
The intervals in the given list do not overlap.
The intervals in different lists may overlap. */

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
        
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).start < list2.get(j).start) {
                results.add(list1.get(i));
                i++;
            } else {
                results.add(list2.get(j));
                j++;
            }
        }
        while (i < list1.size()) {
            results.add(list1.get(i));
            i++;
        }
        while (j < list2.size()) {
            results.add(list2.get(j));
            j++;
        }
        return merge(results);
    }
    
    private List<Interval> merge(List<Interval> intervals) {
        List<Interval> results = new ArrayList<>();
        if (intervals.size() == 0) {
            return results;
        }
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
//time: O(n), space: O(n)