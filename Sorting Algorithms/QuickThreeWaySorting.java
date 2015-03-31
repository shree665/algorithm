
@SuppressWarnings("rawtypes")
public class QuickThreeWaySorting {

	@SuppressWarnings("unchecked")
	public static void sort(Comparable[] a, int low, int hi) {
		if (hi <= low) {
			return;
		}
		
		int lt = low;
		int i = low + 1;
		int gt = hi;
		Comparable v = a[low];
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) {
				exch(a, lt++, i++);
			} else if (cmp > 0) {
				exch(a, i, gt--);
			} else {
				i++;
			}
		}
		sort(a,low, lt - 1);
		sort(a, gt + 1, hi);
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	public static void main(String[] args) {
		

	}

}
