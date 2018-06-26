/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee item : employees) { //smart!!!!
            map.put(item.id, item);
        }
        int count = 0;
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));
        while (!queue.isEmpty()) {
            Employee employee = queue.poll();
            count += employee.importance;
            int size = employee.subordinates.size();
            for (int i = 0; i < size; i++) {
                queue.offer(map.get(employee.subordinates.get(i)));
            }
        }
        return count;
    }
}

/* 算法：BFS
** 难点：利用hashMap去对应id和相应的employee
** 时间复杂度：O(n^2) */