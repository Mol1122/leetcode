/* Description
Give two users' ordered online time series, and each section records the user's login time point x and offline time point y. Find out the time periods when both users are online at the same time, and output in ascending order.you need return a list of intervals.


Example
Example 1:

Input: seqA = [(1,2),(5,100)], seqB = [(1,6)]
Output: [(1,2),(5,6)]
Explanation: In these two time periods (1,2), (5,6), both users are online at the same time.
Example 2:

Input: seqA = [(1,2),(10,15)], seqB = [(3,5),(7,9)]
Output: []
Explanation: There is no time period, both users are online at the same time.   */

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
     * @param seqA: the list of intervals
     * @param seqB: the list of intervals
     * @return: the time periods
     */
    public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
        List<Interval> results = new ArrayList<>();
        if (seqA == null || seqA.size() == 0 || 
            seqB == null || seqB.size() == 0) {
            return results;
        }
        List<Pair> list = new ArrayList<>();
        for (Interval interval : seqA) {
            list.add(new Pair(interval.start, 0));
            list.add(new Pair(interval.end, 1));
        }
        for (Interval interval : seqB) {
            list.add(new Pair(interval.start, 0));
            list.add(new Pair(interval.end, 1));
        }
        Collections.sort(list, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                if (a.time == b.time) {
                    return b.isStart - a.isStart;
                }
                return a.time - b.time;
            }
        });
        int count = 0;
        Interval temp = new Interval(-1, -1);
        boolean flag = false; //has found intersection or not

        for (Pair p : list) {
            if (p.isStart == 0) {
                count++;
            } else {
                count--;
            }
            if (flag) {
                if (count <= 1) {
                    temp.end = p.time;
                    results.add(temp);
                    temp = new Interval(-1, -1);
                    flag = false;
                }
            } else {
                if (count > 1) {
                    temp.start = p.time;
                    flag = true;
                }
            }
        }
        return results;
    }
}

class Pair {
    int time, isStart;
    
    public Pair(int time, int isStart) {
        this.time = time;
        this.isStart = isStart;
    }
}