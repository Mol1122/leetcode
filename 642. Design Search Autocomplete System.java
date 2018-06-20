class AutocompleteSystem {
    TrieNode root;
    String prefix;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";
        
        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }
    
    private void add(String str, int time) {
        TrieNode curr = root;
        for (char c : str.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            curr.count.put(str, curr.count.getOrDefault(str, 0) + time);  //important!! 每个字母都保存着这个sentence出现的个数 
        }
        curr.isWord = true;
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<String>();
        }
        
        prefix = prefix + c;
        TrieNode curr = root;
        for (char cc : prefix.toCharArray()) {
            TrieNode next = curr.children.get(cc);
            if (next == null) {
                return new ArrayList<String>();
            }
            curr = next;
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c));
        for (String s : curr.count.keySet()) {
            pq.add(new Pair(s, curr.count.get(s)));
        }

        List<String> res = new ArrayList<String>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().s);
        }
        return res;
    }
}
                                                         
                                                         

class TrieNode {
    Map<Character, TrieNode> children;
    Map<String, Integer> count;
    boolean isWord;
    
    public TrieNode() {
        children = new HashMap<>();
        count = new HashMap<>();
        isWord = false;
    }
}

class Pair {
    String s;
    int c;
    
    public Pair(String s, int c) {
        this.s = s;
        this.c = c;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

/* 算法：比普通TrieTree多一点的是每个node都有一个count去计数
** 难点： 21行之所以用s作为key是因为每次返回值的时候是返回的potential complete sentence */