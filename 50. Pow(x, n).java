class Solution {
    public double myPow(double x, int n) {
        boolean isNegative = false;
        if (n < 0) {
            isNegative = true;
            x = 1 / x;
            n = -(n + 1); //avoid overflow, n = Math.MIN_VALUE
        }
        double ans = 1, temp = x;
        while (n != 0) {
            if (n % 2 == 1) {
                ans *= temp;
            }
            temp *= temp;
            n /= 2;
        }
        if (isNegative) {
            ans *= x;
        }
        return ans;
    }
}

/* 算法：转化成二进制， 6 = 110，从右往左，是一的要乘以一然后加到最终答案上
** 时间复杂度：O(logn) */

/* public double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        List<Long> digits = new ArrayList<>();
        
        while (n != 0) {
            digits.add(n % 2);
            n /= 2;
        }
        double ans = 1, tmp = x;
        for (Long item : digits) {
            if (item == 1) {
                ans *= item;
            }
            item *= item;
        }
        return ans;
    } */