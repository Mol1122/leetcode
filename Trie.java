class TrieNode {
    int count; //how many words are on this subtree
	Map<Character, TrieNode> children;
	boolean isWord;

	public TrieNode() {
		children = new HashMap<>();
		isWord = false;
	}
}

public class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

   
    public void insert(String word) {
        TrieNode curr = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.isWord;
    }

    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }

    public boolean delete(String word) {
        if (!search(word)) {
            return false;
        }
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode next = curr.children.get(c);
            next.count--;
            if (next.count == 0) {
                curr.children.remove(word.charAt(i)); //remove leaf, 到中间就知道只剩一个了
                return true;
            }
            curr = curr.children.get(c);
        }
        curr.isWord = false; //remove 中间，中间的下面还有另外一个单词
        return true;
    }

    private TrieNode searchNode(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.children.containsKey(c)) {
                return null;
            }
            curr = curr.children.get(c);
        }
        return curr;
    }

    public List<String> findAllWordsWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode matchNode = searchNode(prefix);
        if (prefix == null || prefix.length() == 0 || matchNode == null) {
            return results;
        }
        findAllWordsUnderNode(matchNode, new StringBuilder(prefix), results);
        return results;
    }

    private void findAllWordsUnderNode(TrieNode curr, StringBuilder sb, List<String> results) {
        if (curr.isWord) {
            results.add(new String(sb));
        }
        for (char c : curr.children.keySet()) {
            sb.append(c);
            findAllWordsUnderNode(curr.children.get(c), sb, results);
            sb.remove(sb.length() - 1);
        }
    }
}


