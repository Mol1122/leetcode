/* Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

 

Example 1:


Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 */

 class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int[][] results = new int[k][2];
        if (points == null || points.length < k) {
            return results;
        }
        Queue<int[]> maxheap = new PriorityQueue<>(k, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                int diff = getDistance(b) - getDistance(a);
                if (diff == 0) {
                    if (a[0] == b[0]) {
                        return b[1] - a[1];
                    } else {
                        return b[0] - a[0];
                    }
                }
                return diff;
            }
        });
        for (int[] point : points) {
            maxheap.offer(point);
            if (maxheap.size() > k) {
                maxheap.poll();
            }
        }
        for (int i = 0; i < k; i++) {
            results[i] = maxheap.poll();
        }
        return results;
    }
    
    private int getDistance(int[] a) {
        return (a[0] * a[0]) + (a[1] * a[1]);
    } 
}
//time: O(nlogk), space: O(k)