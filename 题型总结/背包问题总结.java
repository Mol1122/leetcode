/* 92. Backpack
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack? */
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        //动态规划班
        //时间复杂度：O(nm), 空间复杂度：O(nm)
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 1; i <= m; i++) {
            f[0][i] = false;
        }
        
        //遍历第i个物品
        for (int i = 1; i <= n; i++) {
            //遍历所有可能的重量0~m
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j]; //不放最后一个物品
                if (j >= A[i - 1]) { //放最后一个物品
                    f[i][j] |= f[i - 1][j - A[i - 1]];
                }
            }
        }
        //从大到小，找出可能的最大重量
        for (int i = m; i >= 0; i--) {
            if (f[n][i]) {
                return i;
            }
        }
        return 0;
        
        //终极优化
        //时间复杂度：O(nm), 空间复杂度：O(m)
        // if (A == null || A.length == 0) {
        //     return 0;
        // }
        // int n = A.length;
        // boolean[] f = new boolean[m + 1];
        
        // f[0] = true;
        // for (int i = 1; i <= m; i++) {
        //     f[i] = false;
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = m; j >= A[i - 1]; j--) {
        //         f[j] |= f[j - A[i - 1]];
        //     }
        // }
        // for (int i = m; i >= 0; i--) {
        //     if (f[i]) {
        //         return i;
        //     }
        // }
        // return 0;
        
        
        
        // int n = A.length;
        // int[] f = new int[m + 1];
        
        // for (int i = 0; i < n; i++) {
        //     for (int j = m; j >= A[i]; j--) {
        //         f[j] = Math.max(f[j], f[j - A[i]] + A[i]);
        //     }
        // }
        // return f[m];
    }
}

/* 动态规划班思想：
** 时间复杂度：O(MN),
** 优化后空间复杂度：O(M), 难点：不可以用mod2去优化row

算法：f[i][w] = 能否用前i个物品拼出重量w
可以把每个物品的大小当做每个物品的价值，这样就可以用0-1背包的问题解决 */

/* 563. Backpack V
Given n items with size nums[i] which an integer array and all positive numbers. An integer target denotes the size of a backpack. 
Find the number of possible fill the backpack. */
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] nums, int target) {
        //基础写法
        //时间复杂度：O(n*target), 空间复杂度:O(n*target)
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= target; i++) {
            f[0][i] = 0;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= nums[i - 1]) {
                    f[i][j] += f[i - 1][j - nums[i - 1]];
                }
            }
        }
        return f[n][target];
        
        //空间优化
        // if (nums == null || nums.length == 0) {
        //     return 0;
        // }
        // int n = nums.length;
        // int[][] f = new int[2][target + 1];
        // f[0][0] = 1;
        // for (int i = 1; i <= target; i++) {
        //     f[0][i] = 0;
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 0; j <= target; j++) {
        //         f[i%2][j] = f[(i - 1)%2][j];
        //         if (j >= nums[i - 1]) {
        //             f[i%2][j] += f[(i - 1)%2][j - nums[i - 1]];
        //         }
        //     }
        // }
        // return f[n%2][target];
        
        //终极优化
        ///时间复杂度：O(n*target), 空间复杂度: O(target)
        // if (nums == null || nums.length == 0) {
        //     return 0;
        // }
        // int n = nums.length;
        // int[] f = new int[target + 1];
        
        // f[0] = 1;
        // for (int i = 1; i <= target; i++) {
        //     f[i] = 0;
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = target; j >= nums[i - 1]; j--) {
        //         f[j] += f[j - nums[i - 1]];
        //     }
        // }
        // return f[target];
    }
}

/* 
564. Combination Sum IV
Given an integer array nums with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.
答案里是允许有重复的 */
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackVI(int[] A, int m) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] f = new int[m + 1];
        f[0] = 1;
        
        //跟coin change的解法一样
        for (int j = 0; j <= m; j++) {
            //遍历最后放入背包的物品
            for (int i = 0; i < n; i++) {
                if (j - A[i] >= 0) {
                    f[j] += f[j - A[i]];
                }
            }
        }
        return f[m];
    }
}

/* 算法：f[i] = 有多少种组合能拼出重量i
    f[i] = f[i-A0] + f[i-A1] +…+ f[i-A[N-1]]
** 难点：跟之前的题不同的地方在于，之前是判断数组的最后一个item要不要放进背包。
         这题判断的是放进背包的是哪一个item */

/* 125. Backpack II
Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack? */
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][] f = new int[n + 1][m + 1];
        
        f[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            f[0][i] = -1; //impossible
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i - 1] && f[i - 1][j - A[i - 1]] != -1) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        int res = 0;
        for (int i = m; i >= 0; i--) {
            if (f[n][i] != -1) {
                res = Math.max(res, f[n][i]);
            }
        }
        return res;
        
        
        //终极优化版
        // if (A == null || A.length == 0) {
        //     return 0;
        // }
        // int n = A.length;
        // int[] f = new int[m + 1];
        
        // f[0] = 0;
        // for (int i = 1; i <= m; i++) {
        //     f[i] = -1; //impossible
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = m; j >= A[i - 1]; j--) {
        //         if (f[j - A[i - 1]] != -1) {
        //             f[j] = Math.max(f[j], f[j - A[i - 1]] + V[i - 1]);
        //         }
        //     }
        // }
        // int res = 0;
        // for (int i = m; i >= 0; i--) {
        //     if (f[i] != -1) {
        //         res = Math.max(res, f[i]);
        //     }
        // }
        // return res;
        
        
       
    }
}

/* 算法：f[i][w] = 用前i个物品拼出重量w时最大总价值(-1表示不能拼出w) */

/* 
440. Backpack III
Given n kind of items with size Ai and value Vi( each item has an infinite number available) and a backpack with size m. 
What's the maximum value can you put into the backpack? */
public class Solution {
    /**
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][] f = new int[n + 1][m + 1];
        f[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            f[0][i] = -1; //impossible
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i - 1] && f[i][j - A[i - 1]] != -1) {
                    f[i][j] = Math.max(f[i][j], f[i][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        int res = 0;
        for (int i = m; i >= 0; i--) {
            if (f[n][i] != -1) {
                res = Math.max(res, f[n][i]);
            }
        }
        return res;
        
        //终极优化，计算顺序是从左往右
        // if (A == null || A.length == 0) {
        //     return 0;
        // }
        // int n = A.length;
        // int[] f = new int[m + 1];
        // f[0] = 0;
        // for (int i = 1; i <= m; i++) {
        //     f[i] = -1; //impossible
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         int now = f[j];
        //         if (j >= A[i - 1] && f[j - A[i - 1]] != -1) {
        //             now = Math.max(now, f[j - A[i - 1]] + V[i - 1]);
        //         }
        //         f[j] = now;
        //     }
        // }
        // int res = 0;
        // for (int i = m; i >= 0; i--) {
        //     if (f[i] != -1) {
        //         res = Math.max(res, f[i]);
        //     }
        // }
        // return res;
     }
}

/* 算法：f[i][w] = 前i种物品拼出重量w时最大总价值(-1表示不能拼出w) 
** 难点: 与backupii的唯一区别就是line 23, 这个是根据画图得到的，而不是凭空想 */