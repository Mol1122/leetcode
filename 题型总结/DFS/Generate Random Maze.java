/* Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. For each pair of cells on the corridor, there must exist one and only one path between them. (Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.). The wall is denoted by 1 in the matrix and corridor is denoted by 0.

Assumptions

N = 2K + 1 and K >= 0
the top left corner must be corridor
there should be as many corridor cells as possible
for each pair of cells on the corridor, there must exist one and only one path between them
Examples

N = 5, one possible maze generated is

        0  0  0  1  0

        1  1  0  1  0

        0  1  0  0  0

        0  1  1  1  0

        0  0  0  0  0 */
        
public class Solution {
  int[] dx = {1, 0, -1, 0};
  int[] dy = {0, 1, 0, -1};
  Random rand = new Random();

  public int[][] maze(int n) {
    int[][] maze = new int[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == 0 && j == 0) {
                maze[i][j] = 0;
            } else {
                maze[i][j] = 1;
            }
        }
    }
    generate(maze, 0, 0);
    return maze;
  }

  private void shuffle(int[] order) {
    for (int i = 0; i < order.length; i++) {
        int index = rand.nextInt(order.length - i);
        swap(order, i, i + index);
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void generate(int[][] maze, int x, int y) {
    int[] order = {0, 1, 2, 3};
    shuffle(order);

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[order[i]] * 2;
        int ny = y + dy[order[i]] * 2;

        if (nx >= 0 && nx < maze.length && ny >= 0 && ny < maze[0].length &&
            maze[nx][ny] == 1) {
            maze[x + dx[order[i]]][y + dy[order[i]]] = 0;
            maze[nx][ny] = 0;
            generate(maze, nx, ny);
        }
    }
  }
}
//time: O(4^n), space: O(n^2)