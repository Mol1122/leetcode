package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	    Main m = new Main();
	    String[] lines = {"CREATE: id=13&amount=800&currency=USD"};
	    m.getOwed(lines);
    }

    private int getOwed(String[] lines) {
        Map<String, Pair> map = new HashMap<>();

        for (String line : lines) {
            String[] str = line.split(" ");
            String task = str[0].substring(0, str[0].length() - 1);

            if (task.equals("CREATE") || task.equals("FINALIZE")) {
                String[] info = str[1].split("&");
                String[] ids = info[0].split("=");
                String id = ids[1];
                String[] amounts = info[1].split("=");
                int amount = Integer.parseInt(amounts[1]);
                String[] currencies = info[2].split("=");
                String currency = currencies[1];

                //System.out.println("task = " + task + ", id = " + id + ", amount = " + amount + ", currency = " + currency);
                if (!currency.equals("USD")) {
                    continue;
                }
                if (task.equals("CREATE")) {
                    if (!map.containsKey(id)) {
                        map.putIfAbsent(id, new Pair(amount, true, false, false));
                    } else {
                        Pair pair = map.get(id);
                        pair.amount += amount;
                    }
                } else if (task.equals("FINALIZE")) {
                    if (!map.containsKey(id) || map.get(id).isCreated == false) {
                        continue;
                    } else {
                        Pair pair = map.get(id);
                        pair.amount = amount;
                        pair.isFinalized = true;
                    }
                }
            } else if (task.equals("PAY")) {
                String[] info = str[1].split("&");
                String[] ids = info[0].split("=");
                String id = ids[1];
                if (!map.containsKey(id) || map.get(id).isCreated == false || map.get(id).isFinalized == false) {
                    continue;
                } else {
//                    Pair pair = map.get(id);
//                    pair.amount = 0;
//                    pair.isPaid = true;
                    map.remove(id);
                }
            }

        }
        return 0;
    }
}


class Pair {
    int amount;
    boolean isCreated, isFinalized, isPaid;

    public Pair(int amount, boolean isCreated, boolean isFinalized, boolean isPaid) {
        this.amount = amount;
        this.isCreated = isCreated;
        this.isFinalized = isFinalized;
        this.isPaid = isPaid;
    }
}