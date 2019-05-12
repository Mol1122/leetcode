class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
       
//         if (nums1 == null || nums2 == null) {
//             return null;
//         }
        
//         HashSet<Integer> hash = new HashSet<>();
//         for (int i = 0; i < nums1.length; i++) {
//             hash.add(nums1[i]);
//         }
        
//         HashSet<Integer> resultHash = new HashSet<>();
//         for (int i = 0; i < nums2.length; i++) {
//             if (hash.contains(nums2[i]) && !resultHash.contains(nums2[i])) {
//                 resultHash.add(nums2[i]);
//             }
//         }
        
//         int size = resultHash.size();
//         int[] result = new int[size];
//         int index = 0;
//         for (Integer num : resultHash) {
//             result[index++] = num;
//         }
        
//         return result;
        
        
        
        
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

/* 算法：n, m   n < m               time                    space
    1. hashmap                      O(n + m) (O(n*l + m*l)假设n是小的那个， l是string长度)        O(n)  存短的进hashmap
    2. binary search                O(nlogm)        O(1) sort长的那个
    3.sort two list + merge         O(nlogn + mlogm)        O(1)
                        O(n + m)
*/