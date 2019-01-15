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

