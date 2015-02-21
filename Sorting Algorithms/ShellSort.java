
public class ShellSort {
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a){
		//sort a[] into increasing order
		int n = a.length;
		int h = 1;
		
		while (h < n/3) {
			h = 3 * h + 1; //1,4,13,40,121....
		}
		
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				//insert a[i] among a[i-h], a[i-2*h], a[i-3*h]....
				for (int j = i; j >= h; j--) {
					if (less(a[j], a[j-h])) {
						exch(a, j, j-h);
					} else {
						break;
					}
					h = h/3;
				}
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
	
	/*private static void show(Comparable[] a){
		//print the array on a single line
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i]+ " ");
		}
		StdOut.println();
	}*/
	
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
}
