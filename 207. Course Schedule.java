class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0){
            return true;
        }
        List[] edges = new ArrayList[numCourses]; //index指的是index这节课是，arrayList里面的prerequisite
        int[] numPrerequisite = new int[numCourses]; //index这节课有多少个prerequisite
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
        while (!queue.isEmpty()) {
            int curr = queue.poll();
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
        return count == numCourses;
    }
}

/* 算法：遍历所有的prerequisites把每节课有多少prerequisite记录下来，并且记录每完成一节课，它的下节课也能被完成
** 时间复杂度：O(n^2) 
** 空间复杂度：O(n) */