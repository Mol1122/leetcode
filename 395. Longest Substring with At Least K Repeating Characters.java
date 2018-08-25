class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (k <= 1) {
            return s.length();
        }
        if (s.length() < k) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        // for (char key : map.keySet()) {
        //     System.out.println("key = " + key + " " + map.get(key));
        // }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) < k) {
                sb.setCharAt(i, ',');
            }
        }
        // for (int i = 0; i < s.length(); i++) {
        //     System.out.print(s.charAt(i));
        // }
        String[] strs = sb.toString().split(",");
        
        if (strs.length == 1) {
            return strs[0].length();
        }
        int max = 0;
        for (String str : strs) {
            max = Math.max(max, longestSubstring(str, k));
        }
        return max;
    }
}

/* 算法难点：因为题目要求至少k个重复的character,那么最后的结果中不可能包含少于k个的character。 所以可以以那个char为分隔符，recursion它的左边和右边 */