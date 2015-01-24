package week1;

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
	
	public double maxValueInArray(double[] a){
		double max  = a[0];
		for(int i=1; i< a.length; i++){
			if (a[i] > max) {
				max = a[i];
			}
		}
		return max;
	}
	
	public double averageValueOfArray(double[] a){
		int n = a.length;
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum/n;
	}
}
