package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
//        System.out.println(m.getBinary(161));
        List<Integer> results = m.countingBits((int)161);
//        for (int num : results) {
//            System.out.print(num + ", ");
//        }
//        try {
//            int x = 0;
//            for (x = 1; x < 4; x++);
//            System.out.println(x);
//        } catch (Exception e) {
//
//        } finally {
//            System.out.println("Error");
//        }
        List<Integer> slope = Arrays.asList(5, 1, 2, 3, 3, 4);
        m.climbTheHill(slope);
    }

    public long climbTheHill(List<Integer> slope) {
        if (slope == null || slope.size() == 0) {
            return 0;
        }
        int[] a = new int[slope.size()];
        for (int i = 0; i < slope.size(); i++) {
            a[i] = slope.get(i);
        }
        long sum1 = decreasingHelper(a, slope.size());

        int i = 0, j = a.length - 1;
        while (i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        long sum2 = decreasingHelper(a, slope.size());
        return (long)Math.min(sum1, sum2);
    }

    private long decreasingHelper(int[] a, int n) {
        long sum = 0, dif = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            if (!pq.isEmpty() && pq.element() < a[i]) {
                dif = (long)a[i] - pq.element();
                sum += dif;
                pq.remove();
                pq.add(a[i]);
            }
            pq.add(a[i]);
        }

        return sum;
    }

    public int[][] emailThread(int n, String[][] emails) {
        Map<String, Integer> str2id = new HashMap<>();
        Map<Integer, List<String>> id2bodyList = new HashMap<>();
        int index = 1;

        for (String[] email : emails) {
            String str = "";
            if (email[0].compareTo(email[1]) <= 0) {
                str = email[0] + "_" + email[1];
            } else {
                str = email[1] + "_" + email[0];
            }

            if (email[2].contains("---")) { //not new thread
                int id = str2id.get(str);
//                id2bodyList.putIfAbsent(id, new ArrayList<>());
                id2bodyList.get(id).add(email[2]);
            } else { //new thread
                str2id.put(str, index++);
                id2bodyList.putIfAbsent(str2id.get(str), new ArrayList<>());
                id2bodyList.get(str2id.get(str)).add(email[2]);
            }
        }
        int[][] results = new int[n][2];
        for (int i = 0; i < emails.length; i++) {
            String str = "";
            if (emails[i][0].compareTo(emails[i][1]) <= 0) {
                str = emails[i][0] + "_" + emails[i][1];
            } else {
                str = emails[i][1] + "_" + emails[i][0];
            }
            results[i][0] = str2id.get(str);
            for (int j = 0; j < id2bodyList.get(results[i][0]).size(); j++) {
                if (emails[i][2].equals(id2bodyList.get(results[i][0]).get(j))) {
                    results[i][1] = j;
                }
            }
        }
        return results;
    }

    public List<Integer> countingBits(int n) {
        List<Integer> results = new ArrayList<>();
        String binaryStr = getBinary(n);
        int count = 0, index = 1;
        results.add(0);
        for (int i = 0; i < binaryStr.length(); i++) {
            if (binaryStr.charAt(i) == '1') {
                count++;
                results.add(i + 1);
            }
        }
        results.set(0, count);
        return results;
    }

    private String getBinary(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append(n & 1);
            n = n >> 1;
        }
        sb.reverse();
        return sb.toString();
    }

    private List<Integer> diffWaysToCompute(String input) {
        List<Integer> results = new ArrayList<>();
        if (input == null || input.length() == 0) {
            return results;
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                List<Integer> part1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> part2 = diffWaysToCompute(input.substring(i + 1));

                for (int num1 : part1) {
                    for (int num2 : part2) {
                        int temp = 0;
                        switch (input.charAt(i)) {
                            case '+': temp = num1 + num2;
                                break;
                            case '-': temp = num1 - num2;
                                break;
                            case '*': temp = num1 * num2;
                                break;
                        }
                        results.add(temp);
                    }
                }
            }
        }
        if (results.size() == 0) {
            results.add(Integer.parseInt(input));
        }
        Collections.sort(results);
        return results;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str :  strs) {
            char[] sc = str.toCharArray();
            Arrays.sort(sc);
            String key = String.valueOf(sc);
            map.putIfAbsent(key, new ArrayList<String>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> results = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            int x = s.charAt(0) - 'a';
            String temp = "";
            for (int j = 0; j < s.length(); j++) {
                char c = (char)(s.charAt(j) - x);
                if (c < 'a') {
                    c += 26;
                }
                temp += c;
            }
            map.putIfAbsent(temp, new ArrayList<>());
            map.get(temp).add(s);
        }
        for (String key : map.keySet()) {
            results.add(map.get(key));
        }
        return results;
    }
}
