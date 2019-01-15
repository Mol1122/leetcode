class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> results = new ArrayList<>();
        if (words == null || words.length == 0) {
            return results;
        }
        List<String> temp = new ArrayList<>();
        int length = 0;
        
        for (int i = 0; i < words.length; i++) {
            if (length + words[i].length() + temp.size() <= maxWidth) {
                temp.add(words[i]);
                length += words[i].length();
            } else {
                int space = maxWidth - length;
                int space1;
                //System.out.println(space + "temp size: " + temp.size());
                
                if (temp.size() > 1) {
                    space1 = space / (temp.size() - 1);
                } else {
                    space1 = space;
                }
                //System.out.println("space1: " + space1);
                
                int remaining = space - space1 * (temp.size() - 1);
                //System.out.println("remaining: " + remaining);
                
                results.add(addSpace(temp, space1, remaining, maxWidth));
                temp.clear();
                length = 0;
                i--;
            }
        }
        //System.out.println("temp size: " + temp.size());
        if (temp.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < temp.size(); i++) {
                sb.append(temp.get(i) + " ");
            }
            sb.deleteCharAt(sb.length() - 1);
            for (int i = sb.length(); i < maxWidth; i++) {
                sb.append(" ");
            }
            results.add(sb.toString());
        }
        return results;
    }
    
    private String addSpace(List<String> temp, int space1, int remaining, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < temp.size(); i++) {
            sb.append(temp.get(i));
            if (i == temp.size() - 1) {
                continue;
            }
            for (int j = 0; j < space1; j++) {
                sb.append(" ");
            }
            if (remaining > 0) {
                sb.append(" ");
                remaining--;
            }
            //System.out.println(sb.toString() + "/");
        }
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        return sb.toString();
    }
}