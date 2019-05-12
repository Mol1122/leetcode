class Solution {
    public double myPow(double x, int n) {
        if (x == 0 && n == 0) {
            return Integer.MIN_VALUE;
        } else if (x == 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        if (n > 0) {
            return helper(x, n);
        } else {
            return 1.0 / helper(x, -n);
        }
    }
    
    private double helper(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double half = helper(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
//time: O(logn), space: O(logn)