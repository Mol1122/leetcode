class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> results = new ArrayList<>();
        if (positions == null || positions.length == 0) {
            return results;
        }
       
        UnionFind uf = new UnionFind(n * m);
        int[][] matrix = new int[m][n];
            
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            if (matrix[x][y] != 1) {
                uf.addCount();
                bfs(matrix, m, n, x, y, uf);
                matrix[x][y] = 1;
            }
            results.add(uf.query());
        }
        return results;
    }
    
    private void bfs(int[][] matrix, int m, int n, int x, int y, UnionFind uf) {
        int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.offer(x);
        qy.offer(y);
        
        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();
            
            for (int i = 0; i < 4; i++) {
			    int nx = cx + dx[i];
			    int ny = cy + dy[i];
			    if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length && matrix[nx][ny] == 1) {
				    uf.union(getMatrixId(cx, cy, matrix[0].length), getMatrixId(nx, ny, matrix[0].length));
			    }
		    }
        }
		
		// for (int i = 0; i < 4; i++) {
		// 	int nx = x + dx[i];
		// 	int ny = y + dy[i];
		// 	if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length && matrix[nx][ny] == 1) {
		// 		uf.union(getMatrixId(x, y, matrix[0].length), getMatrixId(nx, ny, matrix[0].length));
		// 	}
		// }
    }
    
    private int getMatrixId(int x, int y, int col) {
        return x * col + y;
    }
}

class UnionFind {
    private int[] father = null;
    private int count = 0;
    
    public UnionFind(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        count = 0;
    }
    
    public void addCount() {
        count++;
    }
    
    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    
    public void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
            count--;
        }
    }
    
    public int query() {
        return count;
    }
}



/* 算法：利用并查集会好做很多，没碰到一个position就count++,向4个方向找，如果有1那么判读是否需要合并。利用union find会好做很多。用最熟悉的数组的方式写father
** 时间复杂度：O(kmn)
** 难点：需要新建matrix, postitions是给出新增加的点，注意提议！！
        bfs里要不要queue都无所谓，写了省脑力。不写也没关系。因为 */