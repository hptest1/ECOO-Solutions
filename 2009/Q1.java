import java.io.*;

public class Q1 {

	static BufferedReader br;
		
	static int output;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tokens = readLine();
		
		int a = toInt(tokens[0]);
		int b = toInt(tokens[1]);
		
		recurse(a, b);
		
		System.out.printf("%d tiles are needed for a %d by %d floor", output, a, b);
	}
	
	public static void recurse(int a, int b) {	
		output++;
		
		int power = 1;
		
		while(power <= a && power <= b) {
			power *= 2;
		}
		power /= 2;
		
		if ( (a - power) > 0 )
			recurse(a - power, power);
		if ( (b - power) > 0)
			recurse(b - power, power);
		if ( (a - power) > 0 && 
				(b - power) > 0)
			recurse(a - power, b - power);

	}

	public static int toInt(String v) {
		return Integer.parseInt(v);
	}
	
	public static String[] readLine() throws IOException {
		return br.readLine().split(" ");
	}

}

class Pair {
	
	int a;
	int b;
	
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public String toString() {
		return this.a + " " + this.b;
	}
}
