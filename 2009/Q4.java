import java.util.ArrayList;
import java.util.Scanner;


public class Q4 {

	public static void main(String args[]){

		Scanner input = new Scanner(System.in);

		String word = input.nextLine();
		String parts[] = word.split("\\W+");
		ArrayList<Integer>[] asciiChar = new ArrayList[parts.length];
		int[] length = new int[parts.length];

		String temp;
		for(int x = 0; x < parts.length; x++){
			asciiChar[x] = new ArrayList<Integer>();
			temp = parts[x].toLowerCase().replaceAll("\\W", parts[x]);
			length[x] = temp.length();
			for(int i = 0; i < temp.length(); i++){
				asciiChar[x].add((int)temp.charAt(i));
			}
		}

		for(int x = 0; x < asciiChar.length; x++){

			if(length[x] == 1){
				decipher(asciiChar[x], 0, true);
			}

			else if(length[x] == 2){
				swap(asciiChar[x], 0, 1);
				decipher(asciiChar[x], 0, false);
				decipher(asciiChar[x], 1, false);
			}
			else if(length[x] % 2 != 0){
				swap(asciiChar[x], 0, length[x] /2);

				int temp2 = asciiChar[x].get(length[x] /2 + 1);
				asciiChar[x].remove(length[x] /2 + 1);				
				asciiChar[x].add(temp2);

				decipher(asciiChar[x], 0, true);
				decipher(asciiChar[x], length[x] /2 , true);
				decipher(asciiChar[x], length[x] - 1, true);

			}
			else{
				swap(asciiChar[x], 0, length[x] / 2 - 1);
				swap(asciiChar[x], length[x] - 1, length[x] / 2);
				decipher(asciiChar[x], 0, false);
				decipher(asciiChar[x], length[x] / 2 - 1, false);
				decipher(asciiChar[x], length[x] / 2, false);
				decipher(asciiChar[x], length[x]- 1, false);
			}


		}

		ArrayList<Character>[] backToChar = new ArrayList[parts.length];
		for(int x = 0; x < parts.length; x++)
			backToChar[x] = new ArrayList<Character>();

		ArrayList<Character> finalDecipher = new ArrayList<Character>();
		decipherIntoChar(asciiChar, backToChar, finalDecipher);

		fillPunctuation(finalDecipher, word);

		for(int x = 0; x < finalDecipher.size(); x++){
			System.out.print(finalDecipher.get(x));
		}
	}

	public static void fillPunctuation(ArrayList<Character> finalDecipher,	String word) {
		char temp4;
		for(int x = 0; x < word.length(); x++){
			temp4 = word.charAt(x);
			if(!Character.isAlphabetic(temp4)){
				finalDecipher.set(x, temp4);
			}


		}

	}

	public static void swap(ArrayList<Integer> asciiChar, int A, int B){
		int temp;
		temp = asciiChar.get(A);
		asciiChar.set(A, asciiChar.get(B));
		asciiChar.set(B, temp);
	}

	public static void decipher(ArrayList<Integer> asciiChar, int A, boolean forward){
		if(forward) {
			if (asciiChar.get(A) == (int) 'z')
				asciiChar.set(A, (int) 'a');
			else
				asciiChar.set(A, asciiChar.get(A) + 1);
		} else {
			if (asciiChar.get(A) == (int) 'a')
				asciiChar.set(A, (int) 'z');
			else
				asciiChar.set(A, asciiChar.get(A) - 1);
		}

	}

	public static void decipherIntoChar(ArrayList<Integer>[] asciiChar, ArrayList<Character>[] backToChar,ArrayList<Character> finalDecipher){
		int temp3;
		for(int x = 0; x < asciiChar.length; x++){

			for(int i = 0; i < asciiChar[x].size(); i++){
				temp3 = asciiChar[x].get(i);
				backToChar[x].add((char) temp3 );

			}

			for(int i = 0; i < backToChar[x].size(); i++){
				finalDecipher.add(backToChar[x].get(i));
			}

			finalDecipher.add(' ');			

		}
	}	

}