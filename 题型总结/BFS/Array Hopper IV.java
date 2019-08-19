/* Given an array A of non-negative integers, you are initially positioned at an arbitrary index of the array. A[i] means the maximum jump distance from that position (you can either jump left or jump right). Determine the minimum jumps you need to reach the right end of the array. Return -1 if you can not reach the right end of the array.

Assumptions

The given array is not null and has length of at least 1.
Examples

{1, 3, 1, 2, 2}, if the initial position is 2, the minimum jumps needed is 2 (jump to index 1 then to the right end of array)

{3, 3, 1, 0, 0}, if the initial position is 2, the minimum jumps needed is 2 (jump to index 1 then to the right end of array)

{4, 0, 1, 0, 0}, if the initial position is 2, you are not able to reach the right end of array, return -1 in this case. */

public class Solution {
  public int minJump(int[] nums, int index) {
    if (nums == null || nums.length == 0) {
        return -1;
    }
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(index);
    int step = -1;

    while (!queue.isEmpty()) {
        step++;
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int idx = queue.poll();
            if (idx == nums.length - 1) {
                return step;
            }
            for (int j = idx - nums[idx]; j <= idx + nums[idx]; j++) {
                if (j < 0 || j == idx) {
                    continue;
                }
                queue.offer(j);
            }
        }
    }
    return -1;
  }
}
//算法：这题的本质是求一个点到另一个点的最短路径。第一层可以走到哪些index, 第二层可以走到哪些index. 没办法用dp因为既可以向左也可以向右走
//time: O(n^2), space: O(n)