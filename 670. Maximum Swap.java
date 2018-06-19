class Solution {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] buckets = new int[10];
        
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i; //记录这个数字最大的index
        }
        for (int i = 0; i < digits.length; i++) {
            for (int j = 9; j > digits[i] - '0'; j--) {
                if (buckets[j] > i) { //保证在它右边
                    char temp = digits[buckets[j]];
                    digits[buckets[j]] = digits[i];
                    digits[i] = temp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        return num;
    }
}

/* 语法：Integer.toString(num)
** 难点：Integer.valueOf(new String(digits))
** 时间复杂度： O(n) */