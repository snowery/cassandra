import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*
 * A simple application to use bloom filter to 
 * check whether an integer is in a member or not
 */
public class MyBloomFilter {
	int size;
	int hashCount;
	BitSet bitset;
	
	public MyBloomFilter(int length, int hashCount) {
		this.size = length;
		this.hashCount = hashCount;
		bitset = new BitSet(size);
	}
	
	public void add(int n) {
		for (int idx : MyHashFunc.hashInt(n, hashCount))
			bitset.set(idx%size);
	}
	
	public boolean get(int n) {
		for (int idx : MyHashFunc.hashInt(n, hashCount)) {
			if(!bitset.get(idx%size)) 
				return false;
		}
		return true;
	}
	
	public void test(int n) {
		if(get(n)) 
			System.out.println(n+ " is a member");
		else
			System.out.println(n+ " is NOT a member");
	}
	
	public static void main(String[] args) {
		MyBloomFilter filter = new MyBloomFilter(100, 3);

		System.out.printf("An Interger size is %d bits.\n", Integer.SIZE);
		System.out.printf("The bitset(100) size is %d bits.\n", filter.bitset.size());
		Random gen = new Random();
		
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			int num = gen.nextInt(1000);
			set.add(num);
			filter.add(num);
		}
//		filter.add(13); // generate the false positive
		
		for (int n : set) {
			filter.test(n);
		}
		
		System.out.println("----------------");
		filter.test(713);
		filter.test(301);
		
	}
}
