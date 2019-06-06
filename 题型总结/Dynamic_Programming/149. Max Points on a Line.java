/* Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can be crossed by a single line in 2D space.

Assumptions

The given array is not null and it has at least 2 points
Examples

<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line) */

/*
* class Point {
*   public int x;
*   public int y;
*   public Point(int x, int y) {
*     this.x = x;
*     this.y = y;
*   }
* }
*/
public class Solution {
  public int most(Point[] points) {
    if (points == null || points.length == 0) {
        return 0;
    }
    int max = 0;
    for (int i = 0; i < points.length; i++) {
        Point seed = points[i];
        int same = 1;
        int sameX = 0;
        int most = 0; //the max number of points that are on the same line as seed
        Map<Double, Integer> slope2count = new HashMap<>();
        for (int j = 0; j < points.length; j++) {
            if (i == j) {
                continue;
            }
            Point temp = points[j];
            if (temp.x == seed.x && temp.y == seed.y) {
                same++;
            } else if (temp.x == seed.x) {
                sameX++;
            } else {
                double slope = (temp.y - seed.y + 0.0) / (temp.x - seed.x);
                slope2count.putIfAbsent(slope, 0);
                slope2count.put(slope, slope2count.get(slope) + 1);
                most = Math.max(most, slope2count.get(slope));
            }
        }
        most = Math.max(most, sameX) + same;
        max = Math.max(max, most);
    }
    return max;
  }
}
//time: O(n^2), space: O(n)