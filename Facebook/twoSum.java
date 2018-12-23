public static List<Pair> twoSum(List<Pair> pairs, int k) {
        List<Pair> results = new ArrayList<>();
        if (pairs == null || pairs.size() == 0) {
            return results;
        }
        Map<String, Pair> map = new HashMap<>(); //because both string and Integer are immutable type

        for (int i = 0; i < pairs.size(); i++) {
            String str = (k - pairs.get(i).x) + "," + (k - pairs.get(i).y);
            //int[] temp = {k - pairs.get(i).x, k - pairs.get(i).y};
            if (map.containsKey(str)) {
                results.add(map.get(str));
                results.add(pairs.get(i));
                return results;
            }
            map.put(pairs.get(i).x + "," + pairs.get(i).y, pairs.get(i));
            //int[] arr = {map.get(i).x, map.get(i).y};
            //map.put(arr, pairs.get(i));
        }
        return results;
}