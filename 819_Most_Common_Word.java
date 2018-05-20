class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph += ".";
        
        if (paragraph == null || banned == null) {
            return null;
        }
        Set<String> bannedSet = new HashSet<String>();
        for (String str : banned) {
            bannedSet.add(str);
        }
        Map<String, Integer> countWord = new HashMap<String, Integer>();
        
        String ans = "";
        int ansCount = 0;
        
        StringBuilder sb = new StringBuilder();
        for (char c : paragraph.toCharArray()) { //check char by char
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (sb.length() > 0) {
                    String finalWord = sb.toString();
                    if (!bannedSet.contains(finalWord)) {
                        countWord.put(finalWord, countWord.getOrDefault(finalWord, 0) + 1);
                        if (countWord.get(finalWord) > ansCount) {
                            ans = finalWord;
                            ansCount = countWord.get(finalWord);
                        }
                    }   
                    sb = new StringBuilder();         
            }
        }
        return ans;
    }
}

 /* 语法难点： hashmap.getOrDefault(finalWord, 0) 
  * 算法： 使用hash去储存world数量，处理方法为一个character一个character去判断 */