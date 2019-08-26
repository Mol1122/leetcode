/* Design a data structure that supports the following two operations: addWord(word) and search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or ..

A . means it can represent any one letter.

Example
Example 1:

Input:
  addWord("a")
  search(".")
Output:
  true
Example 2:

Input:
  addWord("bad")
  addWord("dad")
  addWord("mad")
  search("pad")  
  search("bad")  
  search(".ad")  
  search("b..")  
Output:
  false
  true
  true
  true
Notice
You may assume that all words are consist of lowercase letters a-z. */

public class WordDictionary {
    Trie t = new Trie();
    
    public void addWord(String word) {
        t.insert(word);
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        return t.search(word);
    }
}

class TrieNode {
    char c;
    Map<Character, TrieNode> children;
    boolean isWord;
    
    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }
}

class Trie {
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
        TrieNode curr = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c != '.') {
               if (!curr.children.containsKey(c)) {
                   return false;
               }
            } else {
                for (char nc : curr.children.keySet()) {
                    if (search(word.substring(0, i) + nc + word.substring(i + 1))) {
                        return true;
                    }
                }
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.isWord;
    }
}