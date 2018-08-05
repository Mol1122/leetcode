class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> temp = new ArrayList<>();
        
        int i = 0, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                temp.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] result = new int[temp.size()];
        for (i = 0; i < result.length; i++) {
            result[i] = temp.get(i);
        }
        return result;
    }
}
/* 算法：这题不能用sort + binary search. 因为重复的东西你不能删掉再二分 */