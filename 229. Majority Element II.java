class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, length = nums.length;
        
        for (int i = 0; i < length; i++) {
            if (number1 == nums[i]) {
                count1++;
            } else if (number2 == nums[i]) {
                count2++;
            } else if (count1 == 0) {
                count1++;
                number1 = nums[i];
            } else if (count2 == 0) {
                count2++;
                number2 = nums[i];
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] == number1) {
                count1++;
            } else if (nums[i] == number2) {
                count2++;
            }
        }
        if (count1 > (length / 3)) {
            result.add(number1);
        }
        if (count2 > (length / 3)) {
            result.add(number2);
        }
        return result;
    }
}

/* 算法：有专门的majority count算法, 因为是大于三分之一，那么有两个potential answer. initialize他们，按照Majority Element I的方法去遍历，最后判断是否大于1/3
*/