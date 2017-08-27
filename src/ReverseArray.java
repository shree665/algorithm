
public class ReverseArray {

	public static void main(String[] args) {
		int [] b = {1,2,3,4,5,6,7,8};
		
		reverseArray(b);
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i] + "  ");
		}

	}
	
	public static int[] reverseArray(int[] a) {
		int n = a.length;
		for (int i = 0; i < n/2; i++) {
			int temp = a[i];
			a[i] = a[n - i -1];
			a[n-i -1] = temp;
		}
		
		return a;
	}

}
