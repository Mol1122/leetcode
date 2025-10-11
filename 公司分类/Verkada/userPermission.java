// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

class Main {
    public static void main(String[] args) {
        
        String[][] strs = {{"user_1", "owe", "camera_1"}, 
                           {"user_1", "belongs to", "group_1"},
                           {"group_1", "owe", "camera_1"},
                           {"group_1", "belongs to", "group_2"},
                           {"group_2", "owe", "camera_2"},
                           {"user_2", "owe", "camera_2"}
        };
        
        String result = getPermission(strs);
        System.out.println(result);
    }
    
    private static String getPermission(String[][] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Map<String, List<String>> graph = getGraph(strs);
        
        // for (String key : graph.keySet()) {
        //     System.out.print(key + ", neighbors: ");
        //     for (String neighbor : graph.get(key)) {
        //         System.out.print(neighbor + ", ");
        //     }
        //     System.out.println();
        // }
        
        Set<String> users = getUsers(strs);
        Set<String> cameras = getCamera(strs);
        
        // for (String camera : cameras) {
        //     System.out.println(camera + ", ");
        // }
        Map<String, Set<String>> user2camera = new HashMap<>();
        
        for (String user : users) {
            Set<String> permissions = bfs(graph, user);
            user2camera.putIfAbsent(user, permissions);
            if (permissions.size() == cameras.size()) {
                return user;
            }
        }
        return "";
    }
    
    private static Set<String> bfs(Map<String, List<String>> graph, String user) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(user);
        visited.add(user);
        
        Set<String> permission = new HashSet<>();
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.contains("camera")) {
                permission.add(curr);
            }
            if (!graph.containsKey(curr)) {
                continue;
            }
            System.out.println(curr + ", " + graph.containsKey(curr));
            for (String neighbor : graph.get(curr)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return permission;
    }
    
    private static Set<String> getCamera(String[][] strs) {
        Set<String> list = new HashSet<>();
        
        for (String[] str : strs) {
            if (str[2].contains("camera")) {
                list.add(str[2]);
            }
        }
        return list;
    }
    
    private static Set<String> getUsers(String[][] strs) {
        Set<String> list = new HashSet<>();
        
        for (String[] str : strs) {
            if (str[0].contains("user")) {
                list.add(str[0]);
            }
        }
        return list;
    }
    
    private static Map<String, List<String>> getGraph(String[][] strs) {
        Map<String, List<String>> graph = new HashMap<>();
        
        for (String[] str : strs) {
            graph.putIfAbsent(str[0], new ArrayList<>());
            graph.get(str[0]).add(str[2]);
        }
        return graph;
    }
}