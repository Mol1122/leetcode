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
     * @param intervals: the given k sorted interval lists
     * @return:  the new sorted interval list
     */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        List<Interval> results = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return results;
        }
        int k = intervals.size();
        Queue<Pair> heap = new PriorityQueue<>(k, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return intervals.get(p1.row).get(p1.col).start - 
                       intervals.get(p2.row).get(p2.col).start;
            }
        });
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).size() > 0) {
                heap.offer(new Pair(i, 0));
            }
        }
        while (!heap.isEmpty()) {
            Pair curr = heap.poll();
            results.add(intervals.get(curr.row).get(curr.col));
            curr.col++;
            if (curr.col < intervals.get(curr.row).size()) {
                heap.offer(curr);
            }
        }
        return merge(results);
    }
    
    private List<Interval> merge(List<Interval> intervals) {
        List<Interval> results = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        
        for (Interval i : intervals) {
            if (i.start > end) {
                results.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            } else {
                end = Math.max(end, i.end);
            }
        }
        results.add(new Interval(start, end));
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

/* 算法：第一步把所有interval按照start的顺序sort.第二步是merge intervals */