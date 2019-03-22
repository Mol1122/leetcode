package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
//      int[][] matrix = {{0, 0}, {0, 0}};
//      System.out.println(m.minMalwareSpread(matrix, new int[]{0, 1}));
        List<List<Integer>> matrix = new ArrayList<>();
//        matrix.add(new ArrayList<>(Arrays.asList(1, 0, 0)));
//        matrix.add(new ArrayList<>(Arrays.asList(0, 1, 0)));
//        matrix.add(new ArrayList<>(Arrays.asList(0, 0, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(0, 0)));
        matrix.add(new ArrayList<>(Arrays.asList(0, 0)));

        List<Integer> infectedMechines = new ArrayList<>(Arrays.asList(0, 1));
        List<Integer> results = m.solution(matrix, infectedMechines);
        for (int num : results) {
            System.out.print(num + " ");
        }
    }

    public List<Integer> solution(List<List<Integer>> matrix, List<Integer> infectedMachines) {
        int n = matrix.size();
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 1) {
                    uf.union(i, j);
                }
            }
        }
        int[] malCount = new int[n];
        for (int mal : infectedMachines) {
            malCount[uf.find(mal)]++;
        }

        int maxSize = 0, nodeToRemove = Integer.MAX_VALUE, delete = 0;
        for (int node : infectedMachines) {
            int root_node = uf.find(node); //find the root of node
            int size = uf.size[root_node]; //#node in the set
            int mal = malCount[root_node]; //#malnode in the set

            if (mal == 1) {
                if (matrix.get(node).get(node) == 0) {
                    continue;
                }
                if (size > maxSize) {
                    maxSize = size;
                    nodeToRemove = node;
                    delete = mal;
                } else if (size == maxSize) {
                    nodeToRemove = Math.min(nodeToRemove, node);
                }
            }
        }
        if (nodeToRemove != Integer.MAX_VALUE) {
            return new ArrayList<>(Arrays.asList(nodeToRemove, delete));
        }
//        System.out.println("hello");
        maxSize = 0;
        nodeToRemove = Integer.MAX_VALUE;
        for (int node : infectedMachines) {
            int root_node = uf.find(node);
            int size = uf.size[root_node];

            if (size >= maxSize) {
                maxSize = size;
                nodeToRemove = Math.min(nodeToRemove, node);
            }
        }
        return new ArrayList<>(Arrays.asList(nodeToRemove, 0));
    }
}

class UnionFind {
    int[] father = null;
    int[] size = null;

    public UnionFind(int n) {
        father = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }

    public void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);

        if (root_a != root_b) {
            father[root_a] = root_b;
            size[root_b] += size[root_a];
        }
    }
}
