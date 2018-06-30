class Solution {
    public boolean rotateString(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}

/* 算法：把str两个连起来写，那么肯定包含了所有rotate的方式 */