public class Solution {
    /*
     * @param s: a string
     * @param dict: a set of n substrings
     * @return: the minimum length
     */
    public int minLength(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (dict == null || dict.size() == 0) {
            return s.length();
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        queue.offer(s);
        hash.add(s);
        
        int min = s.length();
        while (!queue.isEmpty()) {
            String str = queue.poll();
            for (String sub : dict) {
                int found = str.indexOf(sub);
                while (found != -1) {
                    String new_str = str.substring(0, found) + str.substring(found + sub.length());
                    if (!hash.contains(new_str)) {
                        queue.offer(new_str);
                        hash.add(new_str);
                        min = Math.min(min, new_str.length()); //组合成的新的string也可能可以出现sub
                    }
                    found = str.indexOf(sub, found + 1); //删掉一个sub,往后再看有没有sub可以删掉
                }
            }
        }
        return min;
    }
}

/* 算法：BFS.遍历所有dict里面的次，然后找到所有s里面出现的地方， 并且加入到queue 去判断新的 string会不会出现sub. 删掉后就是最后的答案
** 时间复杂度: O(dict.size() * s.length()) */