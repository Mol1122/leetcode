class Trie {
    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        HashMap<Character, TrieNode> children = root.children;
        char[] ws = word.toCharArray();
        for (int i = 0; i < ws.length; i++) {
            if (children.containsKey(ws[i])) {
                curr = children.get(ws[i]);
            } else {
                TrieNode newNode = new TrieNode(ws[i]);
                children.put(ws[i], newNode); // 别漏
                curr = newNode;
            }
            children = curr.children; //别漏
            if (i == ws.length - 1) {
                curr.isWord = true;
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (searchWordNodePos(word) == null) {
            return false;
        } else if (searchWordNodePos(word).isWord == true) {
            return true;
        }
        return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (searchWordNodePos(prefix) == null) {
            return false;
        }
        return true;
    }
    
    private TrieNode searchWordNodePos(String s) {
        TrieNode curr = root;
        HashMap<Character, TrieNode> children = root.children;
        char[] ws = s.toCharArray();
        for (int i = 0; i < ws.length; i++) {
            if (children.containsKey(ws[i])) {
                curr = children.get(ws[i]);
                children = curr.children;
            } else {
                return null;
            }
        }
        return curr;
    }
}

class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isWord = false;
    
    public TrieNode() {
        
    }
    
    public TrieNode(char c) {
        this.c = c;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

/* 算法：standard TrieTree */