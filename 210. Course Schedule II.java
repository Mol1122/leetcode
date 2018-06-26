class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List[] edges = new ArrayList[numCourses];
        int[] numPrerequisite = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            numPrerequisite[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numPrerequisite.length; i++) {
            if (numPrerequisite[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        int[] order = new int[numCourses];
        
        while (!queue.isEmpty()) {
            int curr = (int)queue.poll();
            order[count] = curr;
            count++;
            int size = edges[curr].size();
            for (int i = 0; i < size; i++) {
                int pointer = (int)edges[curr].get(i);
                numPrerequisite[pointer]--;
                if (numPrerequisite[pointer] == 0) {
                    queue.offer(pointer);
                }
            }
        }
        if (count == numCourses) {
            return order;
        }
        return new int[0];
    }
}

/* 算法：跟course schedule I一样，唯一区别就是加了order[]
**       BFS的两种实现方式，一种是direction array，另一种就是queue 
** 时间复杂度: O(n^2)
** 空间复杂度: O(n) */