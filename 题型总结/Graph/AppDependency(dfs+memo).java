/* There is app to dependency mapping: A: B, C
    *                                      B: D
    *                                      D:
    * return a list of all apps sorted based on dependency count for each app in decreasing order. Dependency can be inherent
    *
    * time: O(V+E), space: O(V+E)
    * */
public static List<String> sortApp(String[] deps) {
    List<String> results = new ArrayList<>();
    if (deps == null || deps.length == 0) {
        return results;
    }
    Map<String, Set<String>> graph = new HashMap<>();
    Set<String> nodes = new HashSet<>();

    //construct graph
    for (String s : deps) {
        String[] strs = s.split(":");
        String app = strs[0];
        nodes.add(app);
        graph.putIfAbsent(app, new HashSet<>());

        if (strs.length == 1) {
            continue;
        }
        String[] dependencyArr = strs[1].trim().split(",");
        for (String item : dependencyArr) {
            if (item.equals("")) {
                continue;
            }
            nodes.add(item);
            graph.putIfAbsent(item.trim(), new HashSet<>());
            graph.get(app).add(item.trim());
        }
    }

    Map<String, Set<String>> memo = new HashMap<>();
    for (String node : nodes) {
        dfs(node, graph, memo, new HashSet<>());
    }

    results = new ArrayList<>(nodes);
    Collections.sort(results, (a, b) -> memo.getOrDefault(b, Collections.emptySet()).size() -
                                            memo.getOrDefault(a, Collections.emptySet()).size());

    return results;
}

private static Set<String> dfs(String root, Map<String, Set<String>> graph, Map<String, Set<String>> memo, Set<String> visited) {
    if (memo.containsKey(root)) {
        return memo.get(root);
    }
    visited.add(root);
    Set<String> dependency = new HashSet<>();

    for (String neighbor : graph.get(root)) {
        dependency.add(neighbor);
        dependency.addAll(dfs(neighbor, graph, memo, visited));
    }
    visited.remove(root);
    memo.put(root, dependency);
    return dependency;
}