/* 	A->BCD
	B->AEF
	C->AFG
	D->AJK */

public getMutualFriends(String A, String B) {
	List<String> list1 = getFriends(A);
	List<String> list2 = getFriends(B);

	//intersection of two lists
	//....
}

public String friendsRecommendation(String A) { //time:O(nlogn), space:O(n)
	List<String> friends = getFriends(A);
	Queue<Pair> maxheap = new PriorityQueue<>(new Comparator<Pair>() {
		public int compare(Pair a, Pair b) {
			return b.freq - a.freq;
		}
	});
	Map<String, Integer> map = new HashMap<>();

	for (String friend : friends) {
		for (String next : getFriends(friend)) {
			if (next.equals(A)) {
				continue;
			}
			map.putIfAbsent(next, 0);
			map.put(next, map.get(next) + 1);
		}
	}
	for (String s : map.keySet()) {
		Pair p = new Pair(s, map.get(s));
		maxheap.offer(p);
	}
	return maxheap.peek().id;
}

class Pair {
	String id;
	int freq;

	public Pair(String id, int freq) {
		this.id = id;
		this.freq = freq;
	}
}