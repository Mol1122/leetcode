public class UnionFind {
	private int[] father = null;

	public UnionFind(int n) {
		father = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			father[i] = i;
		}
	}

	private int find(int x) {
		if (father[x] == x) {
			return x;
		}
		return father[x] = find(father[x]);
	}

	private void union(int a, int b) {
		int root_a = find(a);
		int root_b = find(b);
		if (root_a != root_b) {
			father[root_a] = root_b;
		}
	}
}