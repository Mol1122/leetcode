/* Find the Kth smallest number s such that s = 2 ^ x * 3 ^ y, x >= 0 and y >= 0, 
x and y are all integers.

Assumptions

K >= 1
Examples

the smallest is 1
the 2nd smallest is 2
the 3rd smallest is 3
the 5th smallest is 2 * 3 = 6
the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
 */
 
public class Solution {
  public int kth(int k) {
    Queue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>() {
        public int compare(Pair a, Pair b) {
            return a.val - b.val;
        }
    });
    minheap.offer(new Pair(0, 0, 1));
    boolean[][] visited = new boolean[k][k];
    visited[0][0] = true;
    int[] dx = {1, 0};
    int[] dy = {0, 1};

    for (int i = 0; i < k - 1; i++) {
        Pair p = minheap.poll();
        for (int j = 0; j < 2; j++) {
            int nx = p.x + dx[j];
            int ny = p.y + dy[j];
            if (!visited[nx][ny]) {
                minheap.offer(new Pair(nx, ny, (int)(Math.pow(2, nx) * Math.pow(3, ny))));
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
//time: O(klogk), space: O(k^2)