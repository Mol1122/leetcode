/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int ans = 0;
        
        for (int i = 1; i < n; i++) {
            if (knows(ans, i)) {
                ans = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (ans != i && knows(ans, i)) {
                return -1;
            }
            if (ans != i && !knows(i, ans)) {
                return -1;
            }
        }
        return ans;
    }
}

/* 算法：第一次遍历，根据celebrity不认识任何人进行打擂台，找到候选名人。
        第二次遍历，判断两个条件，名人不认识任何人and所有人都认识名人 */