/* Description
On a 3x3 board, there are 8 tiles represented by the integers 1 through 8, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

Given an initial state of the puzzle board and final state, return the least number of moves required so that the initial state to final state.

If it is impossible to move from initial state to final state, return -1. 

Example 1:

Input:
[
 [2,8,3],
 [1,0,4],
 [7,6,5]
]
[
 [1,2,3],
 [8,0,4],
 [7,6,5]
]
Output:
4

Explanation:
[                 [
 [2,8,3],          [2,0,3],
 [1,0,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [2,0,3],          [0,2,3],
 [1,8,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [0,2,3],          [1,2,3],
 [1,8,4],   -->    [0,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [1,2,3],          [1,2,3],
 [0,8,4],   -->    [8,0,4],
 [7,6,5]           [7,6,5]
]                 ]
Example 2：

Input:
[[2,3,8],[7,0,5],[1,6,4]]
[[1,2,3],[8,0,4],[7,6,5]]
Output:
-1

                                    */
public class Solution {
    /**
     * @param initState: the initial state of chessboard
     * @param finalState: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    public int minMoveStep(int[][] initState, int[][] finalState) {
        String source = matrixToString(initState);
        String target = matrixToString(finalState);
        
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(source);
        set.add(source);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) {
                    return step;
                }

                for (String next : getNext(curr)) {
                    if (!set.contains(next)) {
                        queue.offer(next);
                        set.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private List<String> getNext(String matrix) {
        List<String> results = new ArrayList<>();
        int index = matrix.indexOf('0');
        int cx = index / 3;
        int cy = index % 3;

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                char[] sc = matrix.toCharArray();
                sc[cx * 3 + cy] = sc[nx * 3 + ny];
                sc[nx * 3 + ny] = '0';
                results.add(new String(sc));
            }
        }
        return results;
    }

    private String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(matrix[i][j]);
            }
        }
        return sb.toString();
    }
}