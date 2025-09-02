/* An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.

 

Example 1:


Input: grid = [" /","/ "]
Output: 2
Example 2:


Input: grid = [" /","  "]
Output: 1
Example 3:


Input: grid = ["/\\","\\/"]
Output: 5
Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.
 */

 class Solution {
    public int regionsBySlashes(String[] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int count = n * n * 4;
        UnionFind uf = new UnionFind(count);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    uf.union(getId(i - 1, j, 2, n), getId(i, j, 0, n));
                }
                if (j > 0) {
                    uf.union(getId(i, j - 1, 1, n), getId(i, j, 3, n));
                }
                if (grid[i].charAt(j) != '/') {
                    uf.union(getId(i, j, 0, n), getId(i, j, 1, n));
                    uf.union(getId(i, j, 3, n), getId(i, j, 2, n));
                }
                if (grid[i].charAt(j) != '\\') {
                    uf.union(getId(i, j, 0, n), getId(i, j, 3, n));
                    uf.union(getId(i, j, 1, n), getId(i, j, 2, n));
                }
            }
        }
        return uf.size;
    }

    private int getId(int x, int y, int k, int n) {
        return (x * n + y) * 4 + k;
    }
}

class UnionFind {
    int[] father;
    int size;

    public UnionFind(int n) {
        father = new int[n];
        size = n;

        for (int i = 0; i < n; i++) {
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
            size--;
        }
    }   
}
//time: O(n^2), space: O(n^2)