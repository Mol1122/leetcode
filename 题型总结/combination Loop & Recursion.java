//given 2D array, [[1, 2, 3], [4, 5], [6, 7, 8]]. pick one element from each row and form a string. Find all 
//possible combinations    
    public List<String> combi(String[] words) {
        List<String> results = new ArrayList<>();
//        if (words == null || words.length == 0) {
//            return results;
//        }
//        dfs(words, 0, 0, "", results);
//        return results;

        //non-recursion
        int[] count = new int[words.length];
        int index = words.length - 1;

        boolean flag = false;
        while (index >= 0) {
            String temp = "";
            for (int i = 0; i < words.length; i++) {
                temp += words[i].charAt(count[i]);
            }
            results.add(temp);
            count[index]++;
            while (count[index] == words[index].length()) {
                if (index == 0) {
                    flag = true;
                    break;
                }
                count[index - 1]++;
                for (int j = index; j < count.length; j++) {
                    count[j] = 0;
                }
                if (count[index - 1] == words[index - 1].length()) {
                    index--;
                } else {
                    index = words.length - 1;
                }
            }
            if (flag) {
                break;
            }
        }
        return results;
    }

    private void dfs(String[] words, int row, int col, String temp, List<String> results) {
        if (row == words.length) {
            results.add(new String(temp));
            return;
        }
        for (int i = col; i < words[row].length(); i++) {
            dfs(words, row + 1, col, temp + words[row].charAt(i), results);
        }
    }

    public List<int[]> twoSum(int[] nums, int target) {
        List<int[]> results = new ArrayList<>();
        return results;
    }