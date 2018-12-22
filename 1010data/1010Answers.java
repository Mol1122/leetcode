package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        System.out.println(m.percentOfWords("abcdefghijklmnopqrstuvwxyz", "test1.txt"));
        System.out.println(m.percentOfWords("aabcd", "test2.txt"));
        System.out.println(m.percentOfWords("hello'", "test3.txt"));
        System.out.println(m.percentOfWords("ab'", "test4.txt"));
        System.out.println(m.percentOfWords("ab'", "test5.txt"));
    }

    public double percentOfWords(String s, String f) {
        if (s == null || s.length() == 0 || f == null || f.length() == 0) {
            return 0.0;
        }

        int[] cntS = new int[256]; //count the number of occurrence of each character in s
        char[] sc = s.toCharArray();
        for (char c : sc) {
            cntS[c]++;
        }
        //obtain the file from the specify directory. Change the path according to the testing machine
        File file = new File("/Users/molchen/Desktop/leetcode/1010data/" + f);
        int totalWords = 0; //the total number of words in file
        int count = 0; //the number of words that can be formed using the letters in s

        try {
            Scanner scanner = new Scanner(file);
            List<String> lines = new ArrayList<>();
            while (scanner.hasNext()) { //read line by line
                lines.add(scanner.nextLine());
            }
            // Assumption1: one world cannot be displayed in two lines. In other words, if two words are in different lines,
            //it's considered as two different words
            // Assumption2: the f file does not contain string "\n"
            for (String str : lines) { //for each line, split the words by space, comma, and exclamation
                String[] str_array = str.split("[\\s+,!r]");
                //System.out.println(str);
                for (String word : str_array) {

                    if (word.trim().equals("")) {
                        continue;
                    }
                    //System.out.println(word);
                    totalWords++;
                    int[] cnt = new int[256]; //count the number of occurrence of each character in word
                    for (char c : word.toCharArray()) {
                        cnt[c]++;
                    }
                    boolean isMatch = true;
                    for (int i = 0; i < 256; i++) {
                        if (cnt[i] > cntS[i]) {
                            isMatch = false;
                            break;
                        }
                    }
                    if (isMatch) {
                        count++;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (double)count / totalWords;
    }
}
