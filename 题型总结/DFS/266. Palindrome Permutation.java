class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() == 0 || set.size() == 1;
    }
//     //TLE
//     boolean isPalin = false;
//     public boolean canPermutePalindrome(String s) {
//         if (s == null || s.length() == 0) {
//             return true;
//         }
//         dfs(s.toCharArray(), 0);
//         return isPalin;
//     }
    
//     private void dfs(char[] sc, int index) {
//         if (index == sc.length) {
//             if (isPalindrome(new String(sc))) {
//                 isPalin = true;
//             }
//             return;
//         }
//         for (int i = index; i < sc.length; i++) {
//             swap(sc, index, i);
//             dfs(sc, index + 1);
//             swap(sc, index, i);
//         }
//     }
    
//     private void swap(char[] sc, int i, int j) {
//         char c = sc[i];
//         sc[i] = sc[j];
//         sc[j] = c;
//     }
    
//     private boolean isPalindrome(String s) {
//         int start = 0, end = s.length() - 1;
//         char[] sc = s.toCharArray();
        
//         while (start < end) {
//             if (sc[start] != sc[end]) {
//                 return false;
//             }
//             start++;
//             end--;
//         }
//         return true;
//     }
}