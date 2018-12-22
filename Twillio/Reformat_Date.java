import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class ReformatDate {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/molchen/Desktop/leetcode/Twillio/dates.txt");
        Scanner sc = new Scanner(file);
        int n = Integer.parseInt(sc.nextLine());

        List<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            //System.out.println(line);
            String[] strs = line.split("\\s+");
            //System.out.println(strs.length);
            String day = getDay(strs[0]);
            String month = getMonth(strs[1]);
            String year = strs[2];

            list.add(year + "-" + month + "-" + day);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/molchen/Desktop/leetcode/Twillio/output_dates.txt"));
        for (String str : list) {
            writer.append(str + "\n");
        }
        writer.close();
        sc.close();
    }

    private static String getMonth(String month) {
        Map<String, String> map = new HashMap<>();
        map.put("Jan", "01");
        map.put("Feb", "02");
        map.put("Mar", "03");
        map.put("Apr", "04");
        map.put("May", "05");
        map.put("Jun", "06");
        map.put("Jul", "07");
        map.put("Aug", "08");
        map.put("Sep", "09");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");

        return map.get(month);
    }

    private static String getDay(String day) {
        int ans = 0;
        char[] sc = day.toCharArray();

        for (int i = 0; i < day.length(); i++) {
            if (Character.isDigit(sc[i])) {
                ans = ans * 10 + sc[i] - '0';
            } else {
                break;
            }
        }
        if (ans < 10) {
            return "0" + ans;
        }
        return ans + "";
    }
}
