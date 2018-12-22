package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> input = new ArrayList<>();
        Map<String, Integer> str2count = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        String file_name = sc.nextLine();
        sc.close();

        // hosts_access_log_00.txt
	    File file = new File("/Users/molchen/Desktop/leetcode/Twilio/" + file_name);
        sc = new Scanner(file);
        while (sc.hasNext()) {
            input.add(sc.nextLine());
        }
        for (String str : input) {
            String[] strs = str.split(" - - ");
            str2count.putIfAbsent(strs[0], 0);
            str2count.put(strs[0], str2count.get(strs[0]) + 1);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/molchen/Desktop/leetcode/Twilio/" + "records_" + file_name));
        System.out.println("/Users/molchen/Desktop/leetcode/Twilio/" + "records_" + file_name);
        for (String key : str2count.keySet()) {
            writer.append(key + " " + str2count.get(key) + "\n");
            //System.out.println(key + " " + str2count.get(key));
        }

        writer.close();
        sc.close();
    }
}