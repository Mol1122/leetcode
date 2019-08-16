/* Given two sorted arrays A and B, of sizes m and n respectively. Define s = a + b, where a is one element from A and b is one element from B. 
Find the Kth smallest s out of all possible s'.

Assumptions

A is not null and A is not of zero length, so as B
K > 0 and K <= m * n
Examples

A = {1, 3, 5}, B = {4, 8}

1st smallest s is 1 + 4 = 5
2nd smallest s is 3 + 4 = 7
3rd, 4th smallest s are 9 (1 + 8, 4 + 5) 
5th smallest s is 3 + 8 = 11 */

public class Solution {
  public int kthSum(int[] A, int[] B, int k) {
    if (A == null || B == null || A.length * B.length < k) {
      return 0;
    }  
    Queue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>(){
      public int compare(Pair a, Pair b) {
        return a.sum - b.sum;
      }
    });
    boolean[][] visited = new boolean[A.length][B.length];
    minheap.offer(new Pair(0, 0, A[0] + B[0]));
    visited[0][0] = true;

    int[] dx = {1, 0};
    int[] dy = {0, 1};

    for (int i = 0; i < k - 1; i++) {
      Pair p = minheap.poll();
      for (int j = 0; j < 2; j++) {
        int nx = p.x + dx[j];
        int ny = p.y + dy[j];

        if (nx < A.length && ny < B.length && !visited[nx][ny]) {
          minheap.offer(new Pair(nx, ny, A[nx] + B[ny]));
          visited[nx][ny] = true;
        }
      }
    }
    return minheap.poll().sum;
  }
}

class Pair {
   int x, y, sum;
   public Pair(int x, int y, int sum) {
     this.x = x;
     this.y = y;
     this.sum = sum;
   }
}
//time: O(klogk), space: O(n * m)