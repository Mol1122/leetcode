class WordDictionary {
    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.hasWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return find(word, 0, root);
    }
    
    private boolean find(String word, int index, TrieNode curr) {
        if (index == word.length()) {
            if (curr.children.size() == 0) {
                return true;
            }
            return false;
        }
        char c = word.charAt(index); //对于每一个word.charAt(index), curr.children里才有它，想想root
        if (curr.children.containsKey(c)) {
            if (index == word.length() - 1 && curr.children.get(c).hasWord) {
                return true;
            }
            return find(word, index + 1, curr.children.get(c));
        } else if (c == '.') {
            boolean result = false;
            for (Map.Entry<Character, TrieNode> child : curr.children.entrySet()) {
                if (index == word.length() - 1 && child.getValue().hasWord) {
                    return true;
                }
                if (find(word, index + 1, child.getValue())) {
                    result = true;
                }
            }
            return result;
        } else {
            return false;
        }
    }
}

class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean hasWord;
    
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        hasWord = false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

/* 算法：TrieTree的运用， 20min写完
** 时间复杂度：O(n)
** 空间复杂度：< O(n) 
** 难点：find里第一个判断： 因为root是index = 0, 当走完最后一个TrieNode的时候，已经不会有childrenle 但是当前并不会告诉你, 是哪个char是hasWord == true */