/* We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique 
(except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.) */

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] results = new int[K][2];
        if (points == null || points.length < K) {
            return results;
        }
        Queue<int[]> maxheap = new PriorityQueue<>(K, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (getDistance(a) == getDistance(b)) {
                    if (a[0] == b[0]) {
                        return b[1] - a[1];
                    } else {
                        return b[0] - a[0];
                    }
                }
                return getDistance(b) - getDistance(a);
            }
        });
        for (int[] point : points) {
            maxheap.offer(point);
            if (maxheap.size() > K) {
                maxheap.poll();
            }
        }
        int index = 0;
        for (int i = 0; i < K; i++) {
            results[i] = maxheap.poll();
        }
        return results;
    }
    
    private int getDistance(int[] a) {
        return (a[0] * a[0]) + (a[1] * a[1]);
    }
}
//time: O(nlogk), space: O(k)