public static Map<Character, Integer> solveEquation(List<String> equations) {
        if (equations == null || equations.size() == 0) {
            return null;
        }
        Map<Character, Integer> ch2value = new HashMap<>();
        Map<Character, List<Character>> graph = constructGraph(equations, ch2value);
        Map<Character, Integer> indegree = getInDegree(graph);

        Queue<Character> queue = new LinkedList<>();
        for (char c: indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()) {
            char c = queue.poll();
            for (char next : graph.get(c)) {
                indegree.put(next, indegree.get(next) - 1);
                ch2value.put(next, ch2value.get(c) + ch2value.get(next));
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        return ch2value;
}

private static Map<Character, Integer> getInDegree(Map<Character, List<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();

        for (Character c : graph.keySet()) {
            indegree.put(c, 0);
        }
        for (char c : graph.keySet()) {
            for (char v : graph.get(c)) {
                indegree.put(v, indegree.get(v) + 1);
            }
        }
        return indegree;
    }

    private static Map<Character, List<Character>> constructGraph(List<String> equations,
                                                            Map<Character, Integer> ch2value) {
        Map<Character, List<Character>> graph = new HashMap<>();

        for (String equation :  equations) {
            String[] strs = equation.split("=");
            if (strs[1].contains("+")) {
                for (char c : strs[1].toCharArray()) {
                    if (c == '+') {
                        continue;
                    }
                    if (Character.isDigit(c)) {
                        ch2value.putIfAbsent(strs[0].charAt(0), 0);
                        ch2value.put(strs[0].charAt(0), ch2value.get(strs[0].charAt(0)) + Integer.parseInt(c + ""));
                        continue;
                    }
                    graph.putIfAbsent(c, new ArrayList<>());
                    graph.get(c).add(strs[0].charAt(0));
                }
            } else {
                if (Character.isDigit(strs[1].charAt(0))) {
                    int val = Integer.parseInt(strs[1]);
                    ch2value.put(strs[0].charAt(0), val);
                } else {
                    graph.putIfAbsent(strs[1].charAt(0), new ArrayList<>());
                    graph.get(strs[1].charAt(0)).add(strs[0].charAt(0));
                }
            }
        }
        return graph;
    }