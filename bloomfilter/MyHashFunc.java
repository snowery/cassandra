/*
 * A simple hash function to return n hash codes for the given key
 */
public class MyHashFunc {
	public static int[] hashInt(int key, int n) {
		int[] hashCodes = new int[n];
		for (int i = 0; i < n; i++) {
			hashCodes[i] = key + i;
		}
		return hashCodes;
	}
}
