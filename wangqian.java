/* Wangqian oa 
[1, 2, 3, 4, 6, 5]
*/


public int minEdit(int[] A) {
	if (A == null || A.length == 0) {
		return 0;
	}
	int[] f = new int[A.length]; //到i位置时的最少改动值，使得数组上升
	int ans = Integer.MAX_VALUE;

	f[0] = 0;
	for (int i = 1; i < A.length; i++) {
		f[i] += f[i - 1];

		if (A[i] >= A[i - 1]) {
			continue;
		}
		int old = A[i];
		while (A[i] < A[i - 1]) {
			A[i]++;
		}
		f[i] += A[i] - old;
		ans = Math.min(f[i], ans);
	}

	f[A.length - 1] = 0;
	for (int i = A.length - 2; i >= 0; i--) {
		System.out.println(f[i]);
		f[i] += f[i + 1];

		if (A[i] >= A[i + 1]) {
			continue;
		}
		int old = A[i]
		while (A[i] < A[i + 1]) {
			A[i]++;
		}
		f[i] += A[i] - old;
		ans = Math.min(f[i], ans);
	}
	return ans;
} 
