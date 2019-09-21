/* Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  
The intersection of two closed intervals is a set of real numbers that is either empty, or can be 
represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists. */

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return new int[0][0];
        }
        List<int[]> intervals = new ArrayList<>();
        for (int[] item : A) {
            intervals.add(item);
        }
        for (int[] item : B) {
            intervals.add(item);
        }
        Collections.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int[] last = intervals.get(0);
        List<int[]> resultList = new ArrayList<>();
        for (int i = 1; i < intervals.size(); i++) {
            int[] curr = intervals.get(i);
            if (curr[0] <= last[1]) {
                int[] temp = new int[2];
                temp[0] = Math.max(curr[0], last[0]);
                temp[1] = Math.min(curr[1], last[1]);
                resultList.add(temp);
                if (curr[1] > last[1]) {
                    last = curr;
                }
            } else {
                last = curr;    
            }        
        }
        int[][] results = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            results[i] = resultList.get(i);
        }
        return results;
    } 
}
//time: O(nlogn), space: O(n)