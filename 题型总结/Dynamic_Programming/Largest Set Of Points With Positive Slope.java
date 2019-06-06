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
  public int largest(Point[] points) {
    if (points == null || points.length == 0) {
        return 0;
    }
    Arrays.sort(points, new Comparator<Point>(){
        public int compare(Point a, Point b) {
            if (a.x == b.x) {
                return b.y - a.y;
            }
            return a.x - b.x;
        }
    });
    //longest asceding subsequence
  /*  int n = points.length;
    int[] f = new int[n];
    int max = 0;

    for (int i = 0; i < n; i++) {
        f[i] = 1;
        for (int j = 0; j < i; j++) {
            if (points[j].y < points[i].y) {
                f[i] = Math.max(f[i], f[j] + 1);
            }
        }
        max = Math.max(max, f[i]);
    }
    return max == 1 ? 0 : max;  */

    int[] minLast = new int[points.length + 1];
    minLast[0] = Integer.MIN_VALUE;
    for (int i = 1; i <= points.length; i++) {
        minLast[i] = Integer.MAX_VALUE;
    }
    for (int i = 0; i < points.length; i++) {
        int index = binarySearch(minLast, points[i].y);
        minLast[index] = points[i].y;
    }
    for (int i = minLast.length - 1; i >= 2; i--) {
        if (minLast[i] != Integer.MAX_VALUE) {
            return i;
        }
    }
    return 0;
  }

  private int binarySearch(int[] minLast, int num) {
    int start = 0, end = minLast.length - 1;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (minLast[mid] <= num) {
            start = mid;
        } else {
            end = mid;
        }
    }
    if (minLast[start] >= num) {
        return start;
    }
    return end;
  }
}
//time: O(nlogn + n^2) 可以优化到 O(nlogn), space: O(n)