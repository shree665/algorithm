
public class SelectionSort {
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a){
		//sort a[] into increasing order
		for (int i = 0; i < a.length; i++) {
			//exchange a[i] with the smallest entry in a[i+1..n]
			int min = i; //index of minimum entry
			for (int j = i+1; j < a.length; j++) {
				if (less(a[j], a[min])) {
					min = j;
				}
				exch(a, i, min);
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	@SuppressWarnings({"rawtypes" })
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	@SuppressWarnings("rawtypes")
	private static void show(Comparable[] a){
		//print the array on a single line
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i]+ " ");
		}
		StdOut.println();
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Comparable[] a){
		//Test whether the array entries are in order
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		//read strings from standard input, sort them and print them
		@SuppressWarnings("deprecation")
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);

	}

}
