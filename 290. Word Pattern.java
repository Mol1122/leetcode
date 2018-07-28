class Solution {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        char[] chars = pattern.toCharArray();
        String[] strs = str.split(" ");
        Map<Character, String> map = new HashMap<>();
        
        if (chars.length != strs.length) {
            return false;
        }
        
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                if (!map.get(chars[i]).equals(strs[i])) {
                    return false;
                }
                continue;
            }
            if (map.containsValue(strs[i])) { //易漏
                return false;
            }
            map.put(chars[i], strs[i]);
        }
        return true;
    }
}

/* 算法：利用hashMap进行一句一句的对应
** 难点：21-23易漏 */