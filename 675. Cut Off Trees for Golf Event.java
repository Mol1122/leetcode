class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return 0;
        }
        int n = forest.size();
        int m = forest.get(0).size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); //语法难点
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (forest.get(i).get(j) > 1) {
                    pq.offer(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        int[] start = new int[2];
        int sum = 0;
        while (!pq.isEmpty()) {
            int[] tree = pq.poll();
            int step = minStep(forest, tree, start, n, m);
            if (step < 0) {
                return -1;
            }
            sum += step;
            start[0] = tree[0];
            start[1] = tree[1];
        }
        return sum;
    }
    
    private int minStep(List<List<Integer>> forest, int[] tree, int[] start, int n, int m) {
        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cn = queue.poll();
                if (cn[0] == tree[0] && cn[1] == tree[1]) {
                    return step;
                }
                
                for (int k = 0; k < 4; k++) {
                    int nx = cn[0] + dx[k];
                    int ny = cn[1] + dy[k];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}

/* 算法：1. 因为要按树的高度排序，那么就把所有树 放入到PriorityQueue
        2. 从起始点开始, 用BFS去计算step数
   难点： 46-47行，如果走到的地方是我下一个数的起点，这一层就走完了，如果一直走不到下个起始点的树，说明不能走完所有树，return -1 
   语法：PriorityQueue的排序使用lamda完成       */