public class Solution {
    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        if (str == null || str.length() == 0) {
            return ' ';
        }
        char[] sc = str.toCharArray();
        Arrays.sort(sc);
        System.out.println(new String(sc));
        
        int i = 0, j = 0;
        
        while (i < sc.length) {
            j = i;
            while (j < sc.length && sc[j] == sc[i]) {
                j++;
            }
            if (j - i == 1) {
                return sc[i];
            }
            i = j;
        }
        return sc[sc.length - 1];
    }
}