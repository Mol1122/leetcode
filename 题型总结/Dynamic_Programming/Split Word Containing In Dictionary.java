/* Given a word, smallest way to split it by concatenating words from a given dictionary? Example:   Dictionary:
bob
cat
rob
bobcat

bobcat    rob    return 2

bobcatro  return -1
 */

public int split(String word, Set<String> dict) {
	if (word == null || word.length() == 0) {
		return 0;
	}
	int n = word.length();
	int[] f = new int[n + 1]; //the min split that the first i characters can form
	f[0] = 0;

	for (int i = 1; i <= n; i++) {
		f[i] = Integer.MAX_VALUE;
		for (int j = 0; j < i; j++) { //the start index of the right substring
			if (f[j] != Integer.MAX_VALUE && dict.contains(word.substring(j, i))) {
				f[i] = Math.min(f[i], f[j] + 1);
			}
		}
	}
	return f[n] == Integer.MAX_VALUE ? -1 : f[n];
}
//time: O(n^3), space: O(n)