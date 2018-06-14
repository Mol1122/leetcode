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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        List<Point> list = new ArrayList<>(intervals.length * 2);
        for (Interval item : intervals) {
            list.add(new Point(item.start, 1));
            list.add(new Point(item.end, 0));
        }
        Collections.sort(list, Point.pointComparator);
        int ans = 0, count = 0;
        for (Point p : list) {
            if (p.flag == 1) {
                count++;
            } else {
                count--;
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}

class Point {
    int time;
    int flag;
    
    public Point(int time, int flag) {
        this.time = time;
        this.flag = flag;
    }
    
    static Comparator<Point> pointComparator = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time) {
                return p1.flag - p2.flag;
            } else {
                return p1.time - p2.time;
            }
        }
    };
}

/* 算法：不能直接sort start然后比较end来做。需要同时sort start和end，一个开始另一个还没结束的时候count就会增加，反之减少，说明可以用同一个回忆室
** 难点：Comparator是怎么写的, 以及Collections.sort怎么写 */