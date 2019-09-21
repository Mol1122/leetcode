/* In an alien language, surprisingly they also use english lowercase letters, 
but possibly in a different order. The order of the alphabet is some 
permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, 
return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], 
hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.)
 According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined 
 as the blank character which is less than any other character (More info). */

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length == 0 || order == null || order.length() == 0) {
            return true;
        }
        int[] ch2order = new int[26];
        int index = 0;
        for (char c : order.toCharArray()) {
            ch2order[c - 'a'] = index++;
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            if (!isValid(words[i], words[i + 1], ch2order)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isValid(String s1, String s2, int[] ch2order) {
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length() && s1.charAt(i) == s2.charAt(j)) {
            i++;
            j++;
        }
        if (i >= s1.length()) {
            return true;
        } else if (j >= s2.length()) {
            return false;
        } else {
            if (ch2order[s1.charAt(i) - 'a'] > ch2order[s2.charAt(j) - 'a']) {
                return false;
            } else {
                return true;
            }
        }
    }
}
//time: O(nm), n = words.length, m = average wordLen, space: O(1)