/* Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

Assumptions

the matrix is not null, N > 0 and M > 0
K > 0 and K <= N * M
Examples

{ {1,  3,   5,  7},

  {2,  4,   8,   9},

  {3,  5, 11, 15},

  {6,  8, 13, 18} }

the 5th smallest number is 4
the 8th smallest number is 6 */

public class Solution {
  public int kthSmallest(int[][] matrix, int k) {
    if (matrix == null || matrix.length == 0) {
      return -1;
    }
    Queue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>() {
      public int compare(Pair a, Pair b) {
        return a.val - b.val;
      }
    });
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    int[] dx = {0, 1};
    int[] dy = {1, 0};

    minheap.offer(new Pair(0, 0, matrix[0][0]));
    visited[0][0] = true;

    for (int i = 0; i < k - 1; i++) {
      Pair p = minheap.poll();
      for (int j = 0; j < 2; j++) {
        int nx = p.x + dx[j];
        int ny = p.y + dy[j];
        if (nx < matrix.length && ny < matrix[0].length && !visited[nx][ny]) {
          minheap.offer(new Pair(nx, ny, matrix[nx][ny]));
          visited[nx][ny] = true;
        }
      }
    }
    return minheap.poll().val;
  }
}

class Pair {
  int x, y, val;

  public Pair(int x, int y, int val) {
    this.x = x;
    this.y = y;
    this.val = val;
  }
}
//time: O(klog(n*m)), space: O(n * m);