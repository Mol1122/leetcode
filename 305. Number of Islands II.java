class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> results = new ArrayList<>();
        if (positions == null || positions.length == 0) {
            return results;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        UnionFind uf = new UnionFind(m, n);
        int count = 0;
        
        int[][] visited = new int[m][n];
        for (int i = 0; i < positions.length; i++) {
            int[] point = positions[i];
            if (visited[point[0]][point[1]] == 0) {
                count++;
                visited[point[0]][point[1]] = 1;
                for (int j = 0; j < 4; j++) {
                    int[] np = new int[2];
                    np[0] = point[0] + dx[j];
                    np[1] = point[1] + dy[j];
                    if (np[0] >= 0 && np[0] < m && np[1] >= 0 && np[1] < n && visited[np[0]][np[1]] == 1) {
                        int id_point = uf.getId(point[0], point[1], n);
                        int id_np = uf.getId(np[0], np[1], n);
                        int root_p = uf.find(id_point);
                        int root_np = uf.find(id_np);
                        if (root_p != root_np) {
                            uf.union(id_point, id_np);
                            count--;
                        }
                    }
                }
            }   
            results.add(count);
        }   
        return results;
    }
}

class UnionFind {
    int[] father = null;
    
    public int getId(int x, int y, int n) { // n is col
        return x * n + y;
    }
    
    public UnionFind(int m, int n) {
        father = new int[m * n];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
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
        }
    }
}

/* 算法：利用并查集会好做很多，没碰到一个position就count++,向4个方向找，如果有1那么判读是否需要合并。利用union find会好做很多。用最熟悉的数组的方式写father
** 时间复杂度：O(kmn) */