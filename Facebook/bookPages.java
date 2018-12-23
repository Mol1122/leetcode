//can I get to the last page of the book
    public static boolean bookPages(Map<Integer, List<Integer>> pages, int num, int end) {
        if (pages == null || pages.size() == 0) {
            return false;
        }
        return dfs(pages, num, end);
    }

    private static boolean dfs(Map<Integer, List<Integer>> pages, int num, int end) {
        if (num == end) {
            return true;
        }
        for (Integer i : pages.get(num)) {
            if (dfs(pages, i, end)) {
                return true;
            }
        }
        return false;
    }