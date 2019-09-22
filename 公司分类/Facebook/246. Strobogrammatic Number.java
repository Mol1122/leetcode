/* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false */

class Solution {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        
        int i = 0, j = num.length() - 1;
        while (i <= j) {
            if (!map.containsKey(num.charAt(i))) {
                return false;
            }
            if (map.get(num.charAt(i)) == num.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        
        return true;
    }
}

/* 算法：找到对应之后用双指针
** 时间复杂度：O(n) */