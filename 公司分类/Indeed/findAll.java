public static String findAll(String s) {
        //only find the duplicates
//        if (s == null || s.length() == 0) {
//            return null;
//        }
//        s = s.toLowerCase();
//        String[] strs = s.split(" ");
//        Set<String> set = new HashSet<>();
//
//        for (String str : strs) {
//            if (set.contains(str)) {
//                return str;
//            } else {
//                set.add(str);
//            }
//        }
//        return "";

        //return the first index that has duplicates
        if (s == null || s.length() == 0) {
            return null;
        }
        s = s.toLowerCase();
        String[] strs = s.split(" ");
        Map<String, Integer> str2index = new HashMap<>();
        String result = "";
        int resultIndex = -1;

        for (int i = 0; i < strs.length; i++) {
            if (str2index.containsKey(strs[i])) {
                if (resultIndex == -1) {
                    result = strs[i];
                    resultIndex = str2index.get(strs[i]);
                } else {
                    if (str2index.get(strs[i]) < resultIndex) {
                        result = strs[i];
                        resultIndex = str2index.get(strs[i]);
                    }
                }
            } else {
                str2index.put(strs[i], i);
            }
        }
        return result;
    }