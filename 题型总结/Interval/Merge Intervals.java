/* Given a set of time intervals in any order, return the total length of the area covered by those intervals.

Assumptions:

The given array of intervals is not null.
None of the intervals in the array is null.
Examples:

{<1,3>, <2,4>, <5,7>, <6,8> }. The total length returned is 6(<1,4> is covered and <5,8> is covered). */

class Solution {
  public int length(List<Interval> intervals) {
    if (intervals == null || intervals.size() == 0) {
      return 0;
    }
    int count = 0;
    Collections.sort(intervals, new Comparator<Interval>(){
      public int compare(Interval a, Interval b) {
        return a.start - b.start;
      }
    });
    Interval last = intervals.get(0);
    for (Interval curr : intervals) {
      if (curr.start > last.end) {
        count += last.end - last.start;
        last = curr;
      } else {
        last.end = Math.max(last.end, curr.end);
      }
    }
    if (last != null) {
      count += last.end - last.start;
    }
    return count;
  }
}
//time: O(nlogn), space: O(1)