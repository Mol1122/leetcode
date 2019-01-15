class Solution {
    public List<String> subdomainVisits(String[] domains) {
        List<String> results = new ArrayList<>();
        if (domains == null || domains.length == 0) {
            return results;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String domain : domains) {
            String[] parts = domain.split(" ");
            int count = Integer.parseInt(parts[0]);
            //System.out.println(parts[1]);
            String[] subs = parts[1].split("\\.");
            map.putIfAbsent(parts[1], 0);
            map.put(parts[1], map.get(parts[1]) + count);
            
            int sum = 0;
            //System.out.println("length of subs = " + subs.length);
            for (String sub :  subs) {
                int length = sub.length();
                if (sum + length + 1 >= parts[1].length()) {//+1因为要skip'.'
                    continue;
                }
                String str = parts[1].substring(sum + length + 1);
                sum += length + 1;
                System.out.println(str);
                if (str == "") {
                    continue;
                }
                map.putIfAbsent(str, 0);
                map.put(str, map.get(str) + count);
            }
        }
        
        for (String key : map.keySet()) {
            String temp = String.valueOf(map.get(key)) + " " + key;
            results.add(temp);
        }
        
        return results;
    }
}