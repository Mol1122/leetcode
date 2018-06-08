class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (count + words[last].length() + 1 > maxWidth) {
                    break;
                } else {
                    count += words[last].length() + 1;
                    last++;
                }
            }
            StringBuilder sb = new StringBuilder();
            int diff = last - index - 1;
            //last line or the number of word is 1 in a line, left justify
            if (last == words.length || diff == 0) { 
                for (int i = index; i < last; i++) {
                    sb.append(words[i] + " ");
                }
                sb.deleteCharAt(sb.length() - 1);
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                //middle justify
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    if (i < last - 1) { //最后一个单词不加space
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            sb.append(" ");
                        }
                    } 
                }
            }
            index = last;
            lines.add(sb.toString());
        }
        return lines;
    }
}
