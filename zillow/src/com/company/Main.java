package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
//        int[] nums = {5, 2, 1, 1, 6};
//        int[] results = m.mergeArray(nums);
//        for (int i = 0; i < results.length; i++) {
//            System.out.print(results[i] + " ");
//        }
//        int[] nums = {2, 4, 6, 10, 8};
//        int[] results = m.kLargest(nums, 3);
//        for (int i = 0; i < results.length; i++) {
//            System.out.print(results[i] + " ");
//        }
//        int[] nums = {4, 6, 3, 1, 2, 9, 11, 19, 14, 15, 20, 13, 12, 8, 10, 17};
//        for (int num : m.find4missing(nums, 20)) {
//            System.out.print(num + " ");
//        }
//        System.out.println(m.changeString("a(a(aa)b"));
//        System.out.println(m.changeString("a(a(aa)))"));
//        System.out.println(m.changeString(")("));
//        List<String> list = new ArrayList<>();
//        list.add("abc");
//        System.out.println(m.getMaxMapping(list));
        List<List<Character>> lists = new ArrayList<>();
        List<Character> list1 = new ArrayList<>(Arrays.asList('A', 'B'));
        List<Character> list2 = new ArrayList<>(Arrays.asList('B', 'C'));
        List<Character> list3 = new ArrayList<>(Arrays.asList('D', 'E'));
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        List<Character> results = m.sortMovie(lists);
        System.out.println(results.size());
        for (char c : results) {
            System.out.print(c + " ");
        }
    }

    


    //{{A, B}, {B, C}, {D, E}} -> {A, B, C, D, E}
    public List<Character> sortMovie(List<List<Character>> lists) {
        List<Character> results = new ArrayList<>();
        if (lists == null || lists.size() == 0) {
            return results;
        }
        Map<Character, List<Character>> map = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        for (List<Character> list :  lists) {
            map.putIfAbsent(list.get(0), new ArrayList<>());
            map.get(list.get(0)).add(list.get(1));

            map.putIfAbsent(list.get(1), new ArrayList<>());

            indegree.putIfAbsent(list.get(1), 0);
            indegree.put(list.get(1), indegree.get(list.get(1)) + 1);
        }
        Queue<Character> queue = new LinkedList<>();
        for (List<Character> list : lists) {
            if (!indegree.containsKey(list.get(0))) {
                queue.offer(list.get(0));
            }
        }
        while (!queue.isEmpty()) {
            char c = queue.poll();
            results.add(c);

            for (char neighbor : map.get(c)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return results;
    }

    //merge two consective numbers and plus one if they have the same value
    //[5,2,1,1,1,1,2, 4] -> [5, 2,2,2,2,4] -> [5,3,3,4] -> [5,4,4] -> [5,5] -> [6]
    //time: O(n^2), space: O(n)
    public int[] mergeArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                list.add(nums[i] + 1);
                i++;
            } else {
                list.add(nums[i]);
            }
        }
        if (nums[nums.length - 1] != nums[nums.length - 2]) {
            list.add(nums[nums.length - 1]);
        }
        boolean hasSame = false;
        int[] results = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
            if (i > 0 && list.get(i) == list.get(i - 1)) {
                hasSame = true;
            }
        }

        if (hasSame) {
            return mergeArray(results);
        }
        return results;
    }

    //???
    public int minTotalDistance(int[][][] grid) {
        return 0;
    }

    //computes sum of first fibonacci numbers
    //time:O(n), space:O(n)
    public int sumOfFibinacci(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] fibo = new int[n + 1];
        fibo[0] = 0;
        fibo[1] = 1;

        int sum = 0;
        sum = fibo[0] + fibo[1];

        for (int i = 2; i <= n; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
            sum += fibo[i];
        }
        return sum;
    }

    //Find Minimum in Rotated Sorted Array
    //time:O(logn), space:O(1)
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        int target = nums[nums.length - 1];

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] < target) {
            return nums[start];
        }
        return nums[end];
    }

    //Partition Equal Subset Sum
    //0-1 backpack problem
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        int m = sum / 2;
        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 1; i <= m; i++) {
            f[0][i] = false;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= nums[i - 1]) {
                    f[i][j] |= f[i - 1][j - nums[i - 1]];
                }
            }
        }
        return f[n][m];
    }

    //climbing stairs
    //time: O(n), space:O(n)
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] f = new int[n + 1];
        f[0] = 1;

        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1];
            if (i >= 2) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    //Find All Anagrams in a String
    //time:O(n), space:O(n)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> results = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return results;
        }
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int[] det = new int[256];

        for (int i = 0; i < pc.length; i++) {
            det[pc[i]]--;
            det[sc[i]]++;
        }
        int absSum = 0;
        for (int i = 0; i < 256; i++) {
            absSum += Math.abs(det[i]);
        }
        if (absSum == 0) {
            results.add(0);
        }
        for (int i = pc.length; i < sc.length; i++) {
            char r = sc[i];
            char l = sc[i - pc.length];
            absSum = absSum - Math.abs(det[r]) - Math.abs(det[l]);

            det[r]++;
            det[l]--;

            absSum = absSum + Math.abs(det[r]) + Math.abs(det[l]);
            if (absSum == 0) {
                results.add(i - pc.length + 1); //i - pc.length是左边需要删掉的index
            }
        }
        return results;
    }

    //Find k largest integer in an array
    public int[] kLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new int[0];
        }
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int[] quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return new int[]{};
        }
        int left = start, right = end;
        int pivot = nums[(start + end) / 2];
//        System.out.println("pivot = " + pivot);

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        if (k <= right) {
            return quickSelect(nums, start, right, k);
        }
        if (k >= left) {
            return quickSelect(nums, left, end, k);
        }
        int[] results = new int[nums.length - k];
        int index = 0;
        for (int i = k; i < nums.length; i++) {
            results[index++] = nums[i];
        }


//        for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i] + " ");
//        }
//        System.out.println();
        return results;
    }

    //Word Ladder
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return -1;
        }
        Set<String> dict = new HashSet<>();
        for (String s : wordList) {
            dict.add(s);
        }
        if (beginWord.equals(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        queue.offer(beginWord);
        hash.add(beginWord);

        int step = 1;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                List<String> nextWords = getNexts(curr, dict);

                for (String next : nextWords) {
                    if (hash.contains(next)) {
                        continue;
                    }
                    if (next.equals(endWord)) {
                        return step;
                    }
                    queue.offer(next);
                    hash.add(next);
                }
            }
        }
        return 0;
    }

    private List<String> getNexts(String s, Set<String> dict) {
        List<String> results = new ArrayList<>();

        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < s.length(); i++) {
                if (c == s.charAt(i)) {
                    continue;
                }
                String next = getNext(s, i, c);
                if (dict.contains(next)) {
                    results.add(next);
                }
            }
        }
        return results;
    }

    private String getNext(String s, int index, char c) {
        char[] sc = s.toCharArray();
        sc[index] = c;
        return new String(sc);
    }

    //find 4 missing numbers in 1-100, given an array with size of 96, guarantee every number in it is unique.
    //time:O(n), space:O(1)
    public Set<Integer> find4missing(int[] nums, int n) {
        Set<Integer> results = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        int i = 0, j = 0;

        while (i < nums.length) {
            while (nums[i] != i && nums[i] < nums.length) {
                int temp1 = nums[i];
                nums[i] = nums[temp1];
                nums[temp1] = temp1;
            }
            i++;
        }
        for (int idx = nums.length; idx <= n; idx++) {
            results.add(idx);
        }
        for (int idx = 0; idx < nums.length; idx++) {
            if (nums[idx] != idx) {
                results.add(idx);
                results.remove(nums[idx]);
            }
        }
        results.remove(0);
        return results;
    }

    //find duplicate number
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(nums, mid) <= mid) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (count(nums, start) <= start) {
            return end;
        }
        return start;
    }

    private int count(int[] nums, int target) {
        int cnt = 0;
        for (int num : nums) {
            if (num <= target) {
                cnt++;
            }
        }
        return cnt;
    }

    //get new string, if () then change them to 0 0, if more ( then 1, if more ) then -1
    public String changeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        int cntLeft = 0, cntRight = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                cntLeft++;
            } else if (c == ')') {
                cntRight++;
            }
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (c == ')') {
                if (cntRight > cntLeft) {
                    sb.append(-1);
                    cntRight--;
                } else {
                    sb.append(String.valueOf(0));
                }
            } else if (c == '(') {
                if (cntRight != 0) {
                    sb.append(String.valueOf(0));
                    cntLeft--;
                    cntRight--;
                } else {
                    sb.append(String.valueOf(1));
                    cntLeft--;
                }
            } else {
                sb.append(c);
            }
        }

        sb.reverse();
        for (int i = 0; i < sb.length() - 1; i++) {
            if (sb.charAt(i + 1) == '-') {
                sb.setCharAt(i + 1, '1');
                sb.setCharAt(i, '-');
            }
        }
        return sb.toString();
    }

    public int getMaxMapping(List<String> input) {
        if (input == null || input.size() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (String s : input) {
            assignWeights(s, map);
        }

        List<Integer> values = new ArrayList<>(map.values());
        Collections.sort(values, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        int multipler = 5;
        int result = 0;
        for (int value : values) {
            result += multipler * value;
            multipler--;
        }
        return result;
    }

    private void assignWeights(String s, Map<Character, Integer> map) {
        char[] sc = s.toCharArray();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int addVal = (int)Math.pow(10, n - 1 - i); //this weight corresponding to the actual value of the numeric
            map.put(sc[i], map.getOrDefault(sc[i], 0) + addVal);
        }
    }

    //find the first unique character
    //[a,b,a,c,b,d,e,c] -> d
    public char firstUnique(char[] arr) {
        if (arr == null || arr.length == 0) {
            return ' ';
        }
        int[] cnt = new int[256];
        for (char c : arr) {
            cnt[c]++;
        }
        for (char c : arr) {
            if (cnt[c] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
