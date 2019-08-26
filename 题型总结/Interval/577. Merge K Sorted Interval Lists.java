/**
 Merge K sorted interval lists into one sorted interval list. You need to merge overlapping intervals too.

Example
Example1

Input: [
  [(1,3),(4,7),(6,8)],
  [(1,2),(9,10)]
]
Output: [(1,3),(4,8),(9,10)]
Example2

Input: [
  [(1,2),(5,6)],
  [(3,4),(7,8)]
]
Output: [(1,2),(3,4),(5,6),(7,8)] */

public class Solution {
    /**
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        List<Interval> results = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return results;
        }
        int k = intervals.size();
        Queue<Pair> minheap = new PriorityQueue<>(k, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return intervals.get(a.row).get(a.col).start - 
                       intervals.get(b.row).get(b.col).start;
            }
        });
        for (int i = 0; i < k; i++) {
            if (intervals.get(i).size() > 0) {
                minheap.offer(new Pair(i, 0));
            }
        }
        while (!minheap.isEmpty()) {
            Pair p = minheap.poll();
            results.add(intervals.get(p.row).get(p.col));
            if (p.col + 1 < intervals.get(p.row).size()) {
                p.col++;
                minheap.offer(p);
            }
        }
        return merge(results);
    }
    
    private List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() == 0) {
            return intervals;
        }
        List<Interval> results = new ArrayList<>();
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

class Pair {
    int row, col;
    
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
//time: O(nlogk), space: O(n)