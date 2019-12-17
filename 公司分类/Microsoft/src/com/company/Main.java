package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Main m = new Main();
//	    Integer[] nums = {1,2,3,4,5,6,7};
//	    Integer[] results = m.sort(nums);
//	    for (int num : results) {
//	        System.out.print(num + ", ");
//        }
//
//        String[] employees = {"a", "b", "c", "d"};
//        System.out.println(m.areRelated(employees, "a", "e"));
        //System.out.println(m.longestSubstring("asaaabcccqmhacb", "abc"));
        //ystem.out.println(m.getLargestChar("aAbBz"));
//        int[] nums = {0, 1, 3, 4, 7, 6, 5, 11, 2, 9};
//        int[] result = m.getStartEnd(nums);
//        System.out.println("result[0] = " + result[0] + " result[1 = " + result[1]);

        List<String> results = m.allPermutation("ABC");
        for (String s : results) {
            System.out.println(s);
        }
    }

    public int researchTheEdge(int[][] matrix) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int n = matrix.length;
        int m = matrix[0].length;
        int x = n - 1, y = 0, dir = 0;

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                return dir;
            }
            if (matrix[nx][ny] == 1) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            x = nx;
            y = ny;
        }
        return -1;
    }

    public List<String> allPermutation(String s) {
        List<String> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }
        dfs(s, 0, new StringBuilder(), results);
        return results;
    }

    private void dfs(String s, int index, StringBuilder sb, List<String> results) {
        if (index == s.length() - 1) {
            sb.append(s.charAt(s.length() - 1));
            results.add(new String(sb));
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        sb.append(s.charAt(index));
        sb.append("_");
        dfs(s, index + 1, sb, results);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(s.charAt(index));
        dfs(s, index + 1, sb, results);
        sb.deleteCharAt(sb.length() - 1);
    }

    public int[] getStartEnd(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return new int[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peekLast()]) {
                start = Math.min(start, stack.pollLast());
                end = Math.max(end, i);
            }
            stack.offerLast(i);
        }
        return new int[]{start, end};
    }

    //给定数组，要按照指定的规则排序
    //1.偶数在前奇数在後
    //2.降序
    public Integer[] sort(Integer[] nums) {
        Arrays.sort(nums, new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                if (a % 2 == 0 && b % 2 != 0) {
                    return -1;
                } else if (a % 2 != 0 && b % 2 == 0) {
                    return 1;
                } else {
                    return b.compareTo(a);
                }
            }
        });
        return nums;
    }

    //Check whether two employees are replated
    public boolean areRelated(String[] employees, String employee1, String employee2) {
        UnionFind uf = new UnionFind(employees.length);
        Map<String, Integer> name2id = new HashMap<>();

        for (int i = 0; i < employees.length; i++) {
            name2id.put(employees[i], i);
        }
        for (int i = 0; i < employees.length - 1; i++) {
            uf.union(i, i + 1);
        }
        if (!name2id.containsKey(employee1) || !name2id.containsKey(employee2)) {
            return false;
        }
        int root1 = uf.find(name2id.get(employee1));
        int root2 = uf.find(name2id.get(employee2));
        return root1 == root2;
    }

    //Find the length of the longest substring that contains the characters in the given string
    public int longestSubstring(String source, String target) {
        if (source == null || target == null || source.length() == 0 || target.length() == 0) {
            return 0;
        }
        Set<Character> targetSet = new HashSet<>();
        for (char c : target.toCharArray()) {
            targetSet.add(c);
        }

        int max = 0;
        char[] sc = source.toCharArray();
        int j = 0;

        for (int i = 0; i < sc.length; i++) {
            while(j < sc.length && targetSet.contains(sc[j])) {
                j++;
            }
            max = Math.max(max, j - i);
            i = j;
            j++;
        }
        return max;
    }
    //source = a a b c a a a, target = ab
    //                 i
    //                       j
    //max = 3

    public Character getLargestChar(String s) {
        boolean[] lower = new boolean[26];
        boolean[] upper = new boolean[26];

        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lower[c - 'a'] = true;
            } else {
                upper[c - 'A'] = true;
            }
        }
        for (int i = 25; i >= 0; i--) {
            if (lower[i] && upper[i]) {
                return (char)(i + 'A');
            }
        }
        return null;
    }

    public String getLowestString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) < stack.peekLast()) {
                return s.substring(0, i - 1) + s.substring(i);
            }
            stack.offerLast(s.charAt(i));
        }
        return s.substring(0, s.length() - 1);
    }
}

class UnionFind {
    int[] father = null;

    public UnionFind(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    public void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);

        if (root_a != root_b) {
            father[root_a] = root_b;
        }
    }
}

class Employee {
    String name;
    int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
}