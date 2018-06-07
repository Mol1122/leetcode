class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<Integer> hash = new HashSet<>();
        HashSet<String> dna = new HashSet<>();
        for (int i = 9; i < s.length(); i++) {
            String subStr = s.substring(i - 9, i + 1);
            int encoded = encode(subStr);
            if (hash.contains(encoded)) {
                dna.add(subStr);
            } else {
                hash.add(encoded);
            }
        }
        List<String> result = new ArrayList<>();
        for (String str : dna) {
            result.add(str);
        }
        return result;
    }
    private int encode(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                sum = sum * 4;
            } else if (s.charAt(i) == 'C') {
                sum = sum * 4 + 1;
            } else if (s.charAt(i) == 'G') {
                sum = sum * 4 + 2;
            } else {
                sum = sum * 4 + 3;
            }
        }
        return sum;
    }
}

/* 算法：利用hash来记录是否已经存在过，存在过就加入dna set */