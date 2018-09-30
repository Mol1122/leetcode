public class Solution {
    /**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        if (length < 0) {
            return -1;
        }
        if (length <= 7) {
            return 1;
        }
        int[] f = new int[length + 1];
        int[] path = new int[length + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        f[1] = 0;
        
        //preparation
        for (int[] conn : connections) {
            path[conn[0]] = conn[1];
        }
        
        for (int i = 2; i <= length; i++) {
            if (i <= 7) {
                f[i] = 1;
            } else {
                for (int j = i - 6; j < i; j++) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
            if (path[i] != 0) {
                f[path[i]] = Math.min(f[i], f[path[i]]);
            }
        }
        return f[length];
    }
}

/* 算法：坐标型dp, f[i] = 到pos i的最少steps
** 时间复杂度：O(n) 
** 空间复杂度：O(n) */