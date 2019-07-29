/* Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
] */

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> results = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strings) {
            int x = s.charAt(0) - 'a';
            String temp = "";
            for (int j = 0; j < s.length(); j++) {
                char c = (char)(s.charAt(j) - x);
                if (c < 'a') {
                    c += 26;
                }
                temp += c;
            }
            map.putIfAbsent(temp, new ArrayList<>());
            map.get(temp).add(s);
        }
        for (String key : map.keySet()) {
            results.add(map.get(key));
        }
        return results;
    }
}
//time: O(n * s.length), space: O(n)