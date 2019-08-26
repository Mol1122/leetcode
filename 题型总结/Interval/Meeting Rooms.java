/* Given an array of meeting time intervals consisting of start and end times 
[[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false. */

public class Solution {
  public boolean canAttendMeetings(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
        return true;
    }
    List<Pair> list = new ArrayList<>();
    for (int[] interval : intervals) {
        list.add(new Pair(interval[0], interval[1]));i
    }
    Collections.sort(list, new Comparator<Pair>(){
        public int compare(Pair a, Pair b) {
            if (a.start == b.start) {
                return a.end - b.end;
            }
            return a.start - b.start;
        }
    });
    Pair last = list.get(0);
    for (int i = 1; i < list.size(); i++) {
        if (list.get(i).start < last.end) {
            return false;
        }
        last = list.get(i);
    }
    return true;
  }
}

class Pair {
    int start, end;
    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
//time: O(nlogn), space: O(n)