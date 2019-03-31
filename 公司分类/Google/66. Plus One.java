class Solution {
    public int[] plusOne(int[] digits) {
        int[] ans = new int[digits.length + 1];
        int addOne = 0;
        
        if (digits == null || digits.length == 0) {
            return digits;
        }
        if (digits[digits.length - 1] == 9) {
            digits[digits.length - 1] = 0;
            addOne = 1;
        } else {
            digits[digits.length - 1] += 1;
            addOne = 0;
        }
    
        for (int i = digits.length - 2; i >= 0; i--) {
            if (digits[i] + addOne > 9) {
                digits[i] = 0;
                addOne = 1;
            } else {
                digits[i] += addOne;
                addOne = 0;
            }
        }
        if (addOne == 1) {
            for (int i = 0; i < digits.length; i++) {
                ans[i + 1] = digits[i];
            }
            ans[0] = addOne;
        }
        if (addOne == 1) {
            return ans;
        } else {
            return digits;
        }
    }
}