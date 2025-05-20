/* Given a string s, return all the palindromic permutations (without duplicates) of it.

You may return the answer in any order. If s has no palindromic permutation, return an empty list.

 

Example 1:

Input: s = "aabb"
Output: ["abba","baab"]
Example 2:

Input: s = "abc"
Output: [] */

class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> results = new ArrayList<>();
        if (s == null) {
            return results;
        }
        int odd = 0;
        String midStr = "";
        List<Character> list = new ArrayList<>();
        Map<Character, Integer> ch2count = new HashMap<>();

        // step 1. build character count map and count odds
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ch2count.putIfAbsent(c, 0);
            ch2count.put(c, ch2count.get(c) + 1);
            odd += ch2count.get(c) % 2 == 0 ? -1 : 1;
        }
        // cannot form any palindromic string
        if (odd > 1) {
            return results;
        }
        // step 2. add half count of each character to list
        for (char c : ch2count.keySet()) {
            int count = ch2count.get(c);
            if (count % 2 != 0) {
                midStr += c;
            }
            for (int i = 0; i < count / 2; i++) {
                list.add(c);
            }
        }
        dfs(list, 0, midStr, results);
        return results;
    }

    private void dfs(List<Character> list, int index, String midStr, List<String> results) {
        if (index == list.size()) {
            StringBuilder sb = new StringBuilder();
            for (char c : list) {
                sb.append(c);
            }
            results.add(sb.toString() + midStr + sb.reverse().toString());
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < list.size(); i++) {
            if (!set.contains(list.get(i))) {
                set.add(list.get(i));
                swap(list, index, i);
                dfs(list, index + 1, midStr, results);
                swap(list, index, i);
            }
        }
    }

    private void swap(List<Character> list, int i, int j) {
        char temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
//time: O(n!), space: O(n)