
public class MergeSort {
	@SuppressWarnings("rawtypes")
	private static Comparable[] aux; //temporary array to hold the merges
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a,0, a.length-1);
	}

	@SuppressWarnings("rawtypes")
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid); //sort left half
		sort(a, mid+1, hi); //sort right half
		merge(a, lo, mid, hi); //Merge result
	}

	@SuppressWarnings({ "rawtypes"})
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		
		for (int k = 0; k <= hi; k++) {
			aux[k] = a[k];
		}
		
		for (int k = 0; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean less(Comparable v, Comparable w){
		System.out.println(v.toString() + "  "+w.toString());
		return v.compareTo(w) < 0;
	}
	
	public static void main(String[] args) {
		Comparable[] a = {76, 33, 95, 18, 87, 12, 70, 69, 22, 60, 11, 59};
		MergeSort.sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		
	}
}
