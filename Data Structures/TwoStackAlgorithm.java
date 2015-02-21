
public class TwoStackAlgorithm {

	public static void main(String[] args) {
		Stack<String> operation = new Stack<String>();
		Stack<Double> value = new Stack<Double>();
		
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("(")) {
				//do nothing
			} else if (s.equals("+")) {
				operation.push(s);
			} else if (s.equals("-")) {
				operation.push(s);
			} else if (s.equals("*")) {
				operation.push(s);
			} else if (s.equals("/")) {
				operation.push(s);
			} else if (s.equals("sqrt")) {
				operation.push(s);
			} else if (s.equals(")")) {
				//pop, evaluate and push back the result if token is ")"
				String opr = operation.pop();
				double val = value.pop();
				
				if (opr.equals("+")) {
					val = value.pop() + val;
				} else if (opr.equals("-")) {
					val = value.pop() - val;
				} else if (opr.equals("-")) {
					val = value.pop() - val;
				} else if (opr.equals("*")) {
					val = value.pop() * val;
				} else if (opr.equals("/")) {
					val = value.pop() / val;
				} else if (opr.equals("sqrt")) {
					val = Math.sqrt(val);
				}
				
				value.push(val);
			} else {
				value.push(Double.parseDouble(s));
			}
		}
		StdOut.println(value.pop());
	}

}
