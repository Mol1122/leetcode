public class Solution {
    /*
     * @param s: A string
     * @return: A string
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        // for (String str : strs) {
        //     System.out.println(str);
        // }
        
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].trim().equals("")) {
                continue;
            }
            
            sb.append(strs[i].trim() + " ");
        }
        return sb.toString();
    }
}

/* 难点：有些string就是“  ” */