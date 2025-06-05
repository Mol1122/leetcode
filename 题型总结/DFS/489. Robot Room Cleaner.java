/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

//Method 1
class Solution {
    public void cleanRoom(Robot robot) {
        Set<String> set = new HashSet<>(); //check if the cell has visited
        int dir = 0;
        
        dfs(robot, set, 0, 0, dir);
    }
    
    private void dfs(Robot robot, Set<String> set, int x, int y, int dir) {
        if (set.contains(x + "->" + y)) {
            return;
        }
        robot.clean();
        set.add(x + "->" + y);
        
        for (int i = 0; i < 4; i++) {    
            if (robot.move()) {
                int nx = x;
                int ny = y;
                if (dir == 0) {
                    nx = x - 1;
                } else if (dir == 1) {
                    ny = y + 1;
                } else if (dir == 2) {
                    nx = x + 1;
                } else {
                    ny = y - 1;
                }
                dfs(robot, set, nx, ny, dir);
                //go back to the start position
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight(); //易漏
            dir = (dir + 1) % 4; //换一个方向
        }
    }
}

//Method 2
class Solution {
    private final int EMPTY = 0;
    private final int BLOCKED = 2;
    private final int CLEANED = 3;

    public void cleanRoom(Robot robot) {
        if (robot == null) {
            return;
        }
        int[][] board = new int[210][420];
        dfs(robot, board, 105, 210, 0);
    }

    private void dfs(Robot robot, int[][] board, int x, int y, int dir) {
        int[] dx = {-1, 0, 1, 0}; //u, r, d, 
        int[] dy = {0, 1, 0, -1};

        robot.clean();
        board[x][y] = CLEANED;

        for (int i = 0; i < 4; i++) {
            int newDir = (dir + i) % 4;
            int nx = x + dx[newDir];
            int ny = y + dy[newDir];

            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && board[nx][ny] != CLEANED) {
                if (robot.move()) {
                    dfs(robot, board, nx, ny, newDir);

                    robot.turnRight();
                    robot.turnRight();
                    robot.move();
                    robot.turnRight();
                    robot.turnRight();
                } 
            }
            robot.turnRight();
        }
    }
}
//time: O(4^mn), space: O(mn)

//Method 3
class Solution {
    public void cleanRoom(Robot robot) {
        if (robot == null) {
            return;
        }
        Set<String> visited = new HashSet<>();
        dfs(robot, visited, 0, 0, 0);
    }

    private void dfs(Robot robot, Set<String> visited, int x, int y, int dir) {
        int[] dx = {-1, 0, 1, 0}; //u,r,d,l
        int[] dy = {0, 1, 0, -1};

        robot.clean();
        visited.add(x + "->" + y);

        for (int i = 0; i < 4; i++) {
            int newDir = (dir + i) % 4;
            int nx = x + dx[newDir];
            int ny = y + dy[newDir];

            if (!visited(nx + "->" + ny)) {
                if (robot.move()) {
                    dfs(robot, visited, nx, ny, newDir);

                    robot.turnRight();
                    robot.turnRight();
                    robot.move();
                    robot.turnRight();
                    robot.turnRight();
                }
            }
            robot.turnRight();
        }
    }
}