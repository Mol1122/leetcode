/* You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

 

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1. */

class Solution {
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        UnionFind uf = new UnionFind(n * n);

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        //System.out.println("nx = " + nx + ", ny = " + ny);

                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                            uf.union(i * n + j, nx * n + ny);
                        }
                    }
                }
            }
        }
        int max = 0;
        boolean allOne = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    allOne = false;
                    Set<Integer> set = new HashSet<>();
                    int totalSize = 1;

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                            int root_neighbor = uf.find(nx * n + ny);
                            int size = uf.size[root_neighbor];

                            if (!set.contains(root_neighbor)) {
                                set.add(root_neighbor);
                                totalSize += size;
                            }
                        }
                    }
                    max = Math.max(max, totalSize);
                }
            }
        }

        if (allOne) {
            return n * n;
        }
        return max;
    }
}

class UnionFind {
    int[] father;
    int[] size;

    public UnionFind(int n) {
        father = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
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
            size[root_b] += size[root_a];
        }
    }

    public int getSize(int x) {
        int root = find(x);
        return size[root];
    }
}

//time: O(M * N), space: O(M * N)