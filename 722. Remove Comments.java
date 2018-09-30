class Solution {
    public List<String> removeComments(String[] source) {
        List<String> results = new ArrayList<>();
        if (source == null || source.length == 0) {
            return results;
        }
        
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for (String line : source) {
            char[] sc = line.toCharArray();
            int i = 0;
            
            while (i < sc.length) {
                if (flag) {
                    if (sc[i] == '*' && i + 1 < sc.length && sc[i + 1] == '/') {
                        i += 2;
                        flag = false;
                    } else {
                        i++;
                    }
                    
                }  else {
                    if (sc[i] == '/' &&  i + 1 < sc.length && sc[i + 1] == '*') {
                        i += 2;
                        flag = true;
                    } else if (sc[i] == '/' && i + 1 < sc.length && sc[i + 1] == '/') {
                        i += 2;
                        break;
                    } else {
                        sb.append(sc[i]);
                        i++;
                    }
                }
            }
           
            if (!flag && !sb.toString().equals("")) {
                results.add(sb.toString());
                sb = new StringBuilder();
            }
            
        }
        return results;
    }
}