class Solution {
    public double myPow(double x, int n) {
        boolean isNegative = false;
        if (n < 0) {
            x = 1 / x;
            n = -(n + 1);
            isNegative = true;
        }
        //temp is base to some power, and the power is binary representation
        double ans = 1, temp = x; 
        while (n != 0) {
            if (n % 2 != 0) {
                ans *= temp;
            }
            temp *= temp; //指针往左一个，那么加上base就需要平方一下
            n /= 2;
        }
        if (isNegative) {
            ans *= x;
        }
        return ans;
    }
}

/* 算法：转化成二进制， 6 = 110，从右往左，是一的要乘以一然后加到最终答案上
** 思维过程：因为要降低O(n)的时间复杂度，那么只能是O(logn), 那么每次需要把n除以2，那么就想到了binary representation, 因为找一个数的bianry representation就是不断除以2. 举个例子， 3^2, n = 2 = 10 in binary = 2^1 * 1 + 2^0 * 0 = 2. 把base加上的话，加号就变成了乘号， 3^(2^1 * 1) * 3^(2^0 * 0)

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