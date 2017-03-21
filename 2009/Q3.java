import java.io.*;

public class Q3 {

	static BufferedReader br;

	static int[] alphaToSearch;
	static String[][] map;
	static final int CAP_A_ASCII = 65;

	public enum Direction {
		LEFT (0),
		RIGHT (1),
		UP (2),
		DOWN (3);

		private int value;

		Direction (int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		br = new BufferedReader(new InputStreamReader(System.in));

		String[] tokens = readLine();

		int col = toInt(tokens[0]);
		int row = toInt(tokens[1]);

		map = new String[row][col];

		String toSearch = readLine()[0];
		alphaToSearch = convertToAlphaArray(toSearch);

		for (int i=0; i<row; i++)
			map[i] = readLine()[0].split("");

		///////////////////////////////////////

		int output = 0;

		for (int i=0; i<row; i++)
			for (int j=0; j<col; j++)
				if (!map[i][j].equals("*")) {
					int[] alphaStub = new int[26];
					search(i, j, alphaStub, Direction.LEFT);
					if (checkEqual(alphaStub))
						output++;
				}
		
		System.out.println("there are " + output + " of " + toSearch);
	}

	public static void search(int row, int col, int[] alphaStub, Direction prevDir) {

		if (withinBound(row, col) && !map[row][col].equals("*")) {

			alphaStub[(int) map[row][col].charAt(0) - CAP_A_ASCII]++;
			map[row][col] = "*";
			
			for (Direction d : Direction.values()) {
				switch (d) {
				case LEFT:
					if (!prevDir.equals(d))
						search(row, col - 1, alphaStub, Direction.RIGHT);
					break;
				case RIGHT:
					if (!prevDir.equals(d))
						search(row, col + 1, alphaStub, Direction.LEFT);
					break;
				case UP:
					if (!prevDir.equals(d))
						search(row - 1, col, alphaStub, Direction.DOWN);
					break;
				case DOWN:
					if (!prevDir.equals(d))
						search(row + 1, col, alphaStub, Direction.UP);
					break;
				}
			}
		}
	}

	public static boolean withinBound(int row, int col) {
		return (row >= 0 && row < map.length) &&
				(col >= 0 && col < map[0].length);
	}

	public static boolean checkEqual(int[] temp) {

		for (int i=0; i<temp.length; i++)
			if (alphaToSearch[i] != temp[i])
				return false;

		return true;
	}

	public static int[] convertToAlphaArray(String toConvert) {
		int[] convertTo = new int[26];

		for (int i=0; i<toConvert.length(); i++)
			convertTo[(int) toConvert.charAt(i) - CAP_A_ASCII]++;

		return convertTo;
	}

	public static String[] readLine() throws IOException {
		return br.readLine().split(" ");
	}

	public static int toInt(String v) {
		return Integer.parseInt(v);
	}
}