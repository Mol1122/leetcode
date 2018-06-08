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
        for (int i = 0; i < n; i++) { //名人检验，因为有可能不存在名人
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

/* 一次调用knows(a, b), true, a认识b, a一定不是名人；  false, a不认识b, b一定不是名人。
** 所以一次询问可以排除一个人，n-1询问后剩下一个人，再对这个人进行名人检验就能确定是否为名人*/