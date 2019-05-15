public class Solution{
	public void checkBlock(int n) {
		if (s == null || s.length() == 0) {
			return;
		}
		getAllBlocks(new char[2 * n], s.length(), s.length(), 0);
	}

	private void getAllBlocks(char[] sc, int left, int right, int pos) {
		if (left == 0 && right == 0) {
			printBlock(sc);
			return;
		}
		if (left > 0) {
			sc[pos] = '{';
			getAllBlocks(sc, left - 1, right, pos + 1);
		}
		if (left < right) {
			sc[pos] = '}';
			getAllBlocks(sc, left, right - 1, pos + 1);
		}
	}

	private void printBlock(char[] sc) {
		int heading = 0;
		for (int i = 0; i < sc.length; i++) {
			if (sc[i] == '{') {
				printSpace(heading);
				System.out.println("if {");
				heading += 2;
			} else {
				heading -= 2;
				printSpace(heading);
				System.out.println("}");
			}
		}
	}

	private void printSpace(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
		System.out.println();
	}
}

//time: O(2^2n), space: O(n)