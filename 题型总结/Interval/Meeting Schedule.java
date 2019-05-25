/* Duration of a meeting could be represented as a time interval using an array [s, e] (s < e) where s means start time and e mean end time. 

Given a list of meeting time intervals[[s0, e0],[s1, e1]......], return the maximum number of meetings a person could attend. 
A person could attend two meetings [si, ei] and [sj, ej] only when ei  < sj.

Example:

Input = [[1,2],[2,3],[3,4],[4,5]]

Output = 2

Explanation: The person could attend two meetings either [[1,2], [3,4]] or [[2,3], [4,5].

  */
public class Solution {
  public int maximumMeetings(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
        return 0;
    }
    List<Interval> list = new ArrayList<>();
    for (int[] interval : intervals) {
        list.add(new Interval(interval[0], interval[1]));
    }
    Collections.sort(list, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) {
            return a.end - b.end;
        }
    });
    List<Interval> results = new ArrayList<>();
    results.add(list.get(0));
    for (int i = 1; i < list.size(); i++) {
        if (list.get(i).start > results.get(results.size() - 1).end) {
            results.add(list.get(i));
        }
    } 
    return results.size();
  }
}

class Interval {
    int start, end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
//算法：greedy, 按照end排序，在保证不会overlap的情况下每次都选结束最早的
//time: O(n), space: O(n)