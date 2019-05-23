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
//算法：这题的本质是求一个点到另一个点的最短路径。没办法用dp因为既可以向左也可以向右走
//time: O(n^2), space: O(n)