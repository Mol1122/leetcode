public class Solution {
    /**
     * @param A: A list of integers
     * @return: A boolean
     */

    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return true;
        }
        //定义dp数组
        boolean[] f = new boolean[A.length];
        f[0] = true; //初始化
        
        for (int i = 1; i < A.length; i++) { //计算顺序：从小往大
            f[i] = false;
            
            // 小问题和大问题的关系，根据转移方程得到
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
