package com.company;

import com.sun.nio.sctp.AbstractNotificationHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Missing_words {
    public static void main(String[] args) {
        String[] results = missing_words("I am using HackerRank to improve programming", "am HackerRank to improve");
        for (String str : results) {
            System.out.println(str);
        }
    }

    public static String[] missing_words(String s, String t) {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String str : t.split("\\s+")) {
            set.add(str);
        }
        for (String str : s.split("\\s+")) {
            if (!set.contains(str)) {
                list.add(str);
            }
        }
        String[] results = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
        }
        return results;
    }
}
