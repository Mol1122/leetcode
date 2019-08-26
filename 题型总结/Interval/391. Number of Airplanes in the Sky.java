/**
 Given an list interval, which are taking off and landing time of the flight. How many airplanes are there at most at the same time in the sky?

Example
Example 1:

Input: [(1, 10), (2, 3), (5, 8), (4, 7)]
Output: 3
Explanation:
The first airplane takes off at 1 and lands at 10.
The second ariplane takes off at 2 and lands at 3.
The third ariplane takes off at 5 and lands at 8.
The forth ariplane takes off at 4 and lands at 7.
During 5 to 6, there are three airplanes in the sky.
Example 2:

Input: [(1, 2), (2, 3), (3, 4)]
Output: 1
Explanation: Landing happen before taking off.
Notice
If landing and taking off of different planes happen at the same time, 
we consider landing should happen at first. */

public class Solution {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        List<Pair> list = new ArrayList<>();
        for (Interval item : airplanes) {
            list.add(new Pair(item.start, 0));
            list.add(new Pair(item.end, 1));
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
        for (Pair p : list) {
            if (p.isStart == 0) {
                count++;
            } else {
                count--;
            }
            max = Math.max(max, count);
        }
        return count;
    }
}

class Pair {
    int time, isStart;
    
    public Pair(int time, int isStart) {
        this.time = time;
        this.isStart = isStart;
    }
}
//time: O(nlogn), space: O(n)