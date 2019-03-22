public static boolean wordOrder(String[] words, char[] order) {
        if (words == null || words.length == 0 || order.length == 0) {
            return true;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length; i++) {
            map.put(order[i], i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int j = 0;
            while (j < word1.length() && j < word2.length()) {
                if (map.get(word1.charAt(j)) > map.get(word2.charAt(j))) {
                    return false;
                } else if (map.get(word1.charAt(j)) == map.get(word2.charAt(j))) {
                    j++;
                } else {
                    break;
                }
            }
            if (j == word2.length() && j < word1.length()) {
                return false;
            }
        }
        return true;
}