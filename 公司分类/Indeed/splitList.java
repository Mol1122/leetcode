public static List<List<Integer>> splitList(List<Integer> source) {
        List<List<Integer>> results = new ArrayList<>();
        if (source == null || source.size() == 0) {
            return results;
        }

        while (source.size() > 0) {
            int i = 0;
            List<Integer> list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            while (i < source.size()) {
                if (!set.contains(source.get(i))) {
                    list.add(source.get(i));
                    set.add(source.get(i));
                    source.remove(i);
                } else {
                    i++;
                }
            }
            results.add(list);
        }
        return results;
}