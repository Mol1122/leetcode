class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        
        Queue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.val - b.val;
            }
        });
        minheap.offer(new Pair(0, 0, matrix[0][0]));
        
        for (int i = 0; i < k - 1; i++) {
            Pair c = minheap.poll();
            
            for (int j = 0; j < 2; j++) {
                int nx = c.x + dx[j];
                int ny = c.y + dy[j];
                
                if (nx < n && ny < m && !visited[nx][ny]) {
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

/* 算法：利用minheap, 把左上角最小的放进heap, 然后poll一个出来，把周围的放进去，poll k-1个之后的下一个就是答案
** 时间复杂度：O(klogk)
** 空间复杂度: O(n) */