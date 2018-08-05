public class Solution {
    /**
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return true;
        }
        boolean[] f = new boolean[A.length];
        f[0] = true;
        
        for (int i = 1; i < A.length; i++) {
            f[i] = false;
            
            for (int j = 0; j < i; j++) {
                if (f[j] && j + A[j] >= i) { //能跳到j,并且能从j跳到i
                    f[i] = true;
                    break;
                }
            }
        }
        return f[A.length - 1];
    }
}