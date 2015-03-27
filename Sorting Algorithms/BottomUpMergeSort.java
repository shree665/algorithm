
@SuppressWarnings("rawtypes")
public class BottomUpMergeSort {

	private static Comparable[] aux;
	
	public static void sort (Comparable[] a) {
		//do lg n passes of pairwise merges
		int n = a.length;
		aux = new Comparable[n];
		for (int i = 1; i < n; i = i + i) { //i = sub array size
			for (int j = 0; j < n - i; j += i + i) { //j = sub array index
				merge(a, j, j+i-1, Math.min(j+i+i-1, n-1));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
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
	
	private static boolean less(Comparable<Comparable<?>> v, Comparable<?> w){
		System.out.println(v.toString() + "  "+w.toString());
		return v.compareTo(w) < 0;
	}
	
	public static void main(String[] args) {
		

	}

}
