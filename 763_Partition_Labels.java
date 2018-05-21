class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) { //store the location where the char last apprears
            last[S.charAt(i) - 'a'] = i;
        }
        int left = 0, right = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            right = Math.max(right, last[S.charAt(i) - 'a']);
            if (i == right) {
                list.add(right - left + 1);
                left = right + 1;
            }
        }
        return list;
    }
}

/* 技巧：学会利用array记录每个character最后出现的位置 */