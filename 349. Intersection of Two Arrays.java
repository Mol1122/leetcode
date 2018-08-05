class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] temp = new int[nums1.length + nums2.length];
        
        int i = 0, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (index == 0 || temp[index - 1] != nums1[i]) {
                    temp[index++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] result = new int[index];
        for (i = 0; i < index; i++) {
            result[i] = temp[i];
        }
        return result;
    }
}

/* 算法：有三种做法
1. hashamp
2. sort + binary search
3. sort + merge O(nlogn + mlogm + n + m), O(n)
*/