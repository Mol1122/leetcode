/* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2. */
public class Solution {
  public int minMeetingRooms(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
        return 0;
    }
    List<Pair> list = new ArrayList<>();
    for (int[] interval : intervals) {
        list.add(new Pair(interval[0], 0));
        list.add(new Pair(interval[1], 1));
    }
    Collections.sort(list, new Comparator<Pair>(){
        public int compare(Pair a, Pair b) {
            if (a.time == b.time) {
                return b.isStart - a.isStart;
            }
            return a.time - b.time;
        }
    });
    int max = 0, count = 0;
    for (Pair item : list) {
        if (item.isStart == 0) {
            count++;
        } else {
            count--;
        }
        max = Math.max(max, count);
    }
    return max;
  }
}

class Pair {
    int time, isStart;
    public Pair(int time, int isStart) {
        this.time = time;
        this.isStart = isStart;
    }
}
//算法： sweeping line
//time: O(nlogn), space: O(n)