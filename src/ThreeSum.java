import java.util.Arrays;


public class ThreeSum {
	
	public static int count(int[] a){
		//counts triples that sum to 0
		Arrays.sort(a);
		int n = a.length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (BinarySearch.rank(-a[i]-a[j], a) > j) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		int[] a = In.readInts(args[0]);
		StdOut.println(count(a));
	}

}
