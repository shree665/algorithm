/**
 * Need to do random shuffle on input to get the maximum performance
 */
@SuppressWarnings("rawtypes")
public class QuickSort {
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int low, int hi) {
		if (hi <= low) {
			return;
		}
		
		int j = partition(a, low, hi); //partition the sub array
		sort(a, low, j-1);
		sort(a, j+1, hi);
		
	}
	
	@SuppressWarnings("unchecked")
	private static int partition(Comparable[] a, int low, int hi) {
		int i = low;
		int j = hi + 1;
		Comparable v = a[low];
		while(true) {
			//scanning right
			while(less(a[++i], v)) {
				if (i == hi) {
					break;
				}
			}
			
			//scanning left
			while(less(v, a[--j])) {
				if (j == low) {
					break;
				}
			}
			
			//check for the scan complete and do exchange
			if (i >= j) {
				break;
			}
			exch(a, i, j);
		}
		//put v = a[j] into position
		exch(a, low, j); 
		return j;
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	private static boolean less(Comparable<Comparable<?>> v, Comparable<?> w){
		System.out.println(v.toString() + "  "+w.toString());
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {
		

	}

}
