/* #5 Given two sorted arrays A and B, with their sizes to be m and n, respectively. 

We can pick one element a from A and the other element b from B, and their sum s is defined to be s = a + b. 

How to find k-th smallest s from all possible values of s. 

Assumption: k < m * n. e.g. A = {1, 3, 5}, B = {2, 3}, k = 1, the result is A[0] + B[0] = 3 k = 2, the result is A[0] + B[1] = 4 k = 3, the result is A[1] + B[0] = 5 */

public class Solution {
	public int kthSmallest(int[] A, int[] B, int k) {
		if (A == null || B == null) {
			return Integer.MAX_VALUE;
		}
		int n = A.length;
		int m = B.length;
		Queue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>(){
			public int compare(Pair a, Pair b) {
				return a.sum - b.sum;
			}
		});
		boolean[][] visited = new boolean[n][m];
		minheap.offer(new Pair(0, 0, A[0] + B[0]));
		visited[0][0] = true;

		int[] dx = {1, 0};
		int[] dy = {0, 1};

		for (int i = 0; i < k - 1; i++) {
			Pair c = minheap.poll();

			for (int j = 0; j < 2; j++) {
				int nx = c.x + dx[j];
				int ny = c.y + dy[j];
				if (nx >= 0 && nx < n && !visited[nx][ny]) {
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