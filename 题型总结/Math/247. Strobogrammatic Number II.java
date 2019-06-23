class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }
        Map<Character, Character> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        init(map, list);
        
        dfs(n, "", "", map, list, results);
        return results;
    }
    
    private void dfs(int n, String left, String right, Map<Character, Character> map, List<Character> list, List<String> results) {
        if (left.length() + right.length() == n) {
            results.add(left + right);
            return;
        }
        if (left.length() + right.length() + 1 == n) {
            for (char c :  list) {
                results.add(left + c + right);
            }
            return;
        }
        for (char c : map.keySet()) {
            if (left.length() == 0 && c == '0') {
                continue;
            }
            dfs(n, left + c, map.get(c) + right, map, list, results);
        }
    }
    
    private void init(Map<Character, Character> map, List<Character> list) {
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        
        list.add('0');
        list.add('1');
        list.add('8');
    }
}
//time: O(5^(n/2)), space: O(n/2)
/* 首先，创建一个Map<Integer, Integer> m来存所有可被翻转以及翻转后的数字。
{0:0, 1:1, 6:9, 8:8, 9:6}

其次，创一个List来存所有翻转后是自身的数字。
[0, 1, 8]

在计算的时候，用一个String left和String right分别表示当前左半部分和右半部分。每一次左边末尾添加一个数i，右边的开头就得添加m.get(i).

如果左边长度与右边长度的和等于n（n是偶数），那么直接将left + right放入result中。如果左右长度之和等于n-1（n是奇数），那么此时left + x + right都是一个答案。其中x是0， 1， 8.

一个值得注意的地方，00不是一个valid答案。所以当left.length() == 0时，避开0. */