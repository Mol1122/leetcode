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