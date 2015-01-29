

public class Week1Practice {
	
	public int gcd(int p, int q){
		if (q==0) {
			return p;
		} 
		else {
			int r = p % q;
			return gcd(q,r);
		}
	}
	
	public Double maxValueInArray(Double[] a){
		Double max  = a[0];
		for(int i=1; i< a.length; i++){
			if (a[i] > max) {
				max = a[i];
			}
		}
		return max;
	}
	
	public Double averageValueOfArray(Double[] a){
		int n = a.length;
		Double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum/n;
	}
	
	public Double[] copyArray(Double[] a){
		int n = a.length;
		Double[] b = new Double[n];
		for (int i = 0; i < n; i++) {
			b[i] = a[i];
		}
		return b;
	}
	
	public Double[] reverseArray(Double[] a){
		int n = a.length;
		for (int i = 0; i < n/2; i++) {
			Double temp = a[i];
			a[i] = a[n-i-1];
			a[n-i-1] = temp;
		}
		return a;
	}
	
	public boolean isPrime(int n){
		if (n < 2) {
			return false;
		}
		else {
			for (int i = 0; i * i < n; i++) {
				if (n % i == 0) {
					return false;
				}
			}
			return true;
		}
	}
	
	
	public Double sqrt(Double a){
		if (a < 0) {
			return Double.NaN;
		}
		else {
			Double err = 1e-15;
			Double t = a;
			while(Math.abs(t - a/t) > err * t){
				t = (a/t + t)/2.0;
			}
			return t;
		}
	}
	/*
	 * Is the string is a palindrome
	 */
	public Boolean isPalindrome(String s){
		int n = s.length();
		for (int i = 0; i < n/2; i++) {
			if (s.charAt(i) != s.charAt(n-1-i)) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Check whether an array of strings is in alphabetical order
	 */
	public Boolean isSorted(String[] a){
		for (int i = 0; i < a.length; i++) {
			if (a[i-1].compareTo(a[i]) > 0) {
				return false;
			}
		}
		return true;
	}
	
	
}
